/**
 * File: HiAPIRequest.kt
 *
 * Description: Represents a generic API request object used for RESTful operations.
 *              Encapsulates the endpoint details and optional request body.
 *
 * Author: netcanis
 * Created: 2024-12-26
 *
 * License: MIT
 */

package com.mwkg.network.rest

/**
 * A generic data class for API requests.
 *
 * @param T The type of the request body.
 * @property endpoint The endpoint for the API request.
 * @property body The optional request body for the API call.
 */
data class HiAPIRequest<T>(
    val endpoint: HiEndpoint, // API endpoint details
    val body: T? = null       // Optional request body
)