/**
 * File: HiNetworkManager.kt
 *
 * Description: A network manager for handling API requests and responses. It supports
 *              Bearer token authentication, JSON-based requests, and asynchronous callback handling.
 *              Uses OkHttp for HTTP operations and Gson for JSON parsing.
 *
 * Author: netcanis
 * Created: 2024-12-26
 *
 * License: MIT
 */

package com.mwkg.network.rest

import android.content.Context
import android.util.Log
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * A singleton object that manages network requests using OkHttp.
 */
object HiNetworkManager {
    private var bearerToken: String? = null // Stores the Bearer token for authentication
    private const val TOKEN_KEY = "HiBearerToken" // SharedPreferences key for storing the token
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val gson = Gson() // Gson instance for JSON serialization/deserialization

    /**
     * Sets the Bearer token and saves it in SharedPreferences.
     *
     * @param token The Bearer token to be used for authenticated requests.
     * @param context The Android context to access SharedPreferences.
     */
    fun setBearerToken(token: String, context: Context) {
        bearerToken = token
        val sharedPreferences = context.getSharedPreferences("HiNetworkPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }

    /**
     * Clears the Bearer token from memory and SharedPreferences.
     *
     * @param context The Android context to access SharedPreferences.
     */
    fun clearBearerToken(context: Context) {
        bearerToken = null
        val sharedPreferences = context.getSharedPreferences("HiNetworkPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().remove(TOKEN_KEY).apply()
    }

    /**
     * Sends a generic API request and parses the response into a `HiAPIResponse`.
     *
     * @param T The type of the expected response data.
     * @param apiRequest The API request containing endpoint details and optional body.
     * @param baseUrl The base URL of the API.
     * @param callback A callback invoked with the result of the network call.
     */
    fun <T> request(
        apiRequest: HiAPIRequest<T>,
        baseUrl: String,
        callback: (Result<HiAPIResponse<T>>) -> Unit
    ) {
        val url = "$baseUrl${apiRequest.endpoint.path}"
        val requestBuilder = Request.Builder().url(url).method(apiRequest.endpoint.method.name, null)

        bearerToken?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        val request = requestBuilder.build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(Result.failure(e))
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    callback(Result.failure(IOException("Unexpected code $response")))
                    return
                }

                response.body?.string()?.let { body ->
                    val type = object : TypeToken<HiAPIResponse<T>>() {}.type
                    val apiResponse: HiAPIResponse<T> = gson.fromJson(body, type)
                    callback(Result.success(apiResponse))
                } ?: callback(Result.failure(IOException("Empty response body")))
            }
        })
    }

    /**
     * Sends a simplified API request with parameters and returns the raw `Response`.
     *
     * @param endpoint The API endpoint details.
     * @param baseUrl The base URL of the API.
     * @param parameters Optional parameters to be included in the request body.
     * @param callback A callback invoked with the raw `Response` object or an error.
     */
    fun request(
        endpoint: HiEndpoint,
        baseUrl: String,
        parameters: Map<String, Any>?,
        callback: (Result<Response>) -> Unit
    ) {
        val url = "$baseUrl${endpoint.path}"
        val requestBuilder = Request.Builder().url(url).method(endpoint.method.name, null)

        bearerToken?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        if (parameters != null) {
            try {
                val json = gson.toJson(parameters)
                val body = json.toRequestBody("application/json".toMediaTypeOrNull())
                requestBuilder.method(endpoint.method.name, body)
            } catch (e: Exception) {
                callback(Result.failure(e))
                return
            }
        }

        val request = requestBuilder.build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(Result.failure(e))
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    callback(Result.failure(IOException("Unexpected code $response")))
                    return
                }
                callback(Result.success(response))
            }
        })
    }
}
