/**
 * File: HiAPIResponse.kt
 *
 * Description: Represents a generic API response object used for RESTful operations.
 *              Encapsulates the response data, status code, and an optional message.
 *
 * Author: netcanis
 * Created: 2024-12-26
 *
 * License: MIT
 */

package com.mwkg.network.rest

/**
 * A generic data class for API responses.
 *
 * @param T The type of the response data.
 * @property data The response data from the API call.
 * @property statusCode The HTTP status code of the response.
 * @property message An optional message associated with the response (e.g., error details).
 */
data class HiAPIResponse<T>(
    val data: T,             // The data returned by the API
    val statusCode: Int,     // The HTTP status code (e.g., 200 for success)
    val message: String      // A descriptive message, often for errors or additional info
)