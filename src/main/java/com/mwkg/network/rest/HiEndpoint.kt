/**
 * File: HiEndpoint.kt
 *
 * Description: Defines the structure of an API endpoint, including its path, HTTP method,
 *              headers, and query parameters. Provides a generic way to describe RESTful endpoints.
 *              Also includes the `HttpMethod` enum for supported HTTP methods.
 *
 * Author: netcanis
 * Created: 2024-12-26
 *
 * License: MIT
 */

package com.mwkg.network.rest

/**
 * Represents an API endpoint with its path, HTTP method, headers, and query parameters.
 *
 * @property path The endpoint path (e.g., `/users` or `/auth/login`).
 * @property method The HTTP method used for the request (e.g., GET, POST).
 * @property headers Optional HTTP headers to be included in the request.
 * @property queryItems Optional query parameters appended to the URL.
 */
data class HiEndpoint(
    val path: String,                       // API endpoint path
    val method: HttpMethod,                 // HTTP method (e.g., GET, POST)
    val headers: Map<String, String>? = null, // Optional HTTP headers
    val queryItems: Map<String, String>? = null // Optional query parameters
)

/**
 * Enum class representing the supported HTTP methods for API requests.
 */
enum class HttpMethod {
    GET,    // HTTP GET method
    POST,   // HTTP POST method
    PUT,    // HTTP PUT method
    DELETE, // HTTP DELETE method
    PATCH   // HTTP PATCH method
}