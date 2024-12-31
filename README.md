# **feat_network**

A **feature module** for managing REST, WebSocket, and Socket connections in Android applications.

---

## **Overview**

`feat_network` is a versatile Android module designed to:
- Handle RESTful API requests and responses.
- Manage WebSocket connections for real-time communication.
- Provide basic TCP socket connection management.

This module leverages **OkHttp** for HTTP and WebSocket operations and uses **Kotlin Coroutines** for asynchronous handling.

---

## **Features**

- ✅ **REST API Management**: Supports customizable endpoints, request bodies, and headers.
- ✅ **WebSocket Communication**: Handles WebSocket connections, text, and binary messaging.
- ✅ **Socket Connections**: Provides basic TCP socket connection management.
- ✅ **Bearer Token Support**: Includes authentication token management for secure API calls.
- ✅ **Lightweight and Modular**: Easily integrates into existing Android projects.

---

## **Requirements**

| Requirement        | Minimum Version         |
|--------------------|-------------------------|
| **Android OS**     | 6.0 (API 23)            |
| **Kotlin**         | 1.9.22                  |
| **Android Studio** | Giraffe (2022.3.1)      |
| **Gradle**         | 8.0                     |

---

## **Setup**

### **1. Add feat_network to Your Project**

To include `feat_network` via **JitPack**, follow these steps:

1. Add JitPack to your project-level `build.gradle` file:

    ```gradle
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
    ```

2. Add `feat_network` to your module-level `build.gradle` file:

    ```gradle
    dependencies {
        implementation 'com.github.clodymade:feat_network:1.0.2'
    }
    ```

3. Sync your project.

---

## **Usage**

### **REST API**

#### **1. Define an Endpoint**

Use the `HiEndpoint` class to define an endpoint:

```kotlin
val endpoint = HiEndpoint(
    path = "/api/v1/example",
    method = HttpMethod.POST,
    headers = mapOf("Authorization" to "Bearer YOUR_TOKEN")
)
```

### **2. Send a Request**

Send text or binary messages:

```kotlin
HiWebSocketManager().sendText("Hello, WebSocket!")
HiWebSocketManager().sendBinary(byteArrayOf(0x01, 0x02, 0x03))
```

### **3. Disconnect**

Disconnect from the WebSocket server:

```kotlin
HiWebSocketManager().disconnect()
```

---

### **WebSocket**

#### **1. Connect to a WebSocket Server**

Use the `HiWebSocketManager` class to establish a connection:

```kotlin
val webSocketListener = object : WebSocketListener() {
    override fun onMessage(webSocket: WebSocket, text: String) {
        println("Received: $text")
    }
}

HiWebSocketManager().connect("wss://example.com/socket", webSocketListener)
```

### **2. Send Messages**

Send text or binary messages:

```kotlin
HiWebSocketManager().sendText("Hello, WebSocket!")
HiWebSocketManager().sendBinary(byteArrayOf(0x01, 0x02, 0x03))
```

### **3. Disconnect**

Disconnect from the WebSocket server:

```kotlin
HiWebSocketManager().disconnect()
```

---

### **Socket**

#### **1. Connect to a Socket Server**

Use the `HiSocketManager` class to establish a TCP connection:

```kotlin
val socketManager = HiSocketManager("example.com", 12345)
socketManager.connect()
```

### **2. Send a Message**

Send a text message over the socket connection:

```kotlin
socketManager.sendMessage("Hello, Socket!")
```

### **3. Disconnect**

Disconnect from the socket server:

```kotlin
socketManager.disconnect()
```

---

## **License**

feat_network is available under the MIT License. See the LICENSE file for details.

---

## **Contributing**

Contributions are welcome! To contribute:

1. Fork this repository.
2. Create a feature branch:
```
git checkout -b feature/your-feature
```
3. Commit your changes:
```
git commit -m "Add feature: description"
```
4. Push to the branch:
```
git push origin feature/your-feature
```
5. Submit a Pull Request.

---

## **Author**

### **netcanis**
iOS GitHub: https://github.com/netcanis
Android GitHub: https://github.com/clodymade

---
