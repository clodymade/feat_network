/**
 * File: HiWebSocketManager.kt
 *
 * Description: A simple manager for handling WebSocket connections.
 *              Provides methods to connect, send text or binary messages, and disconnect.
 *              Utilizes OkHttp's WebSocket client for implementation.
 *
 * Author: netcanis
 * Created: 2024-12-26
 *
 * License: MIT
 */

package com.mwkg.network.websocket

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

/**
 * A manager for handling WebSocket connections.
 */
class HiWebSocketManager {
    private var webSocket: WebSocket? = null // Active WebSocket instance

    /**
     * Connects to a WebSocket server using the provided URL and listener.
     *
     * @param url The WebSocket server URL.
     * @param listener A WebSocketListener to handle WebSocket events.
     */
    fun connect(url: String, listener: WebSocketListener) {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        webSocket = client.newWebSocket(request, listener)
    }

    /**
     * Sends a text message over the WebSocket connection.
     *
     * @param message The text message to send.
     * @throws IllegalStateException If the WebSocket is not connected.
     */
    fun sendText(message: String) {
        webSocket?.send(message)
    }

    /**
     * Sends binary data over the WebSocket connection.
     *
     * @param data The binary data to send as a ByteArray.
     * @throws IllegalStateException If the WebSocket is not connected.
     */
    fun sendBinary(data: ByteArray) {
        webSocket?.send(ByteString.of(*data))
    }

    /**
     * Disconnects the WebSocket connection with a normal closure (code 1000).
     */
    fun disconnect() {
        webSocket?.close(1000, null)
    }
}
