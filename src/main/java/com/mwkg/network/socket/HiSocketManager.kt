/**
 * File: HiSocketManager.kt
 *
 * Description: A simple manager for handling socket connections.
 *              Provides methods to connect, disconnect, and send messages over a socket.
 *
 * Author: netcanis
 * Created: 2024-12-26
 *
 * License: MIT
 */

package com.mwkg.network.socket

import java.net.Socket

/**
 * A manager for handling TCP socket connections.
 *
 * @property host The host address to connect to.
 * @property port The port number to connect to.
 */
class HiSocketManager(private val host: String, private val port: Int) {

    private var socket: Socket? = null // The socket connection

    /**
     * Establishes a connection to the specified host and port.
     *
     * @throws IOException If the connection fails.
     */
    fun connect() {
        socket = Socket(host, port)
    }

    /**
     * Closes the socket connection if it is open.
     *
     * @throws IOException If an error occurs while closing the socket.
     */
    fun disconnect() {
        socket?.close()
    }

    /**
     * Sends a message over the socket connection.
     *
     * @param message The message to send.
     * @throws IOException If the socket is not connected or an error occurs while sending.
     */
    fun sendMessage(message: String) {
        socket?.getOutputStream()?.write(message.toByteArray())
    }
}