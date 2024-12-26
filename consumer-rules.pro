# Keep all public classes and methods in the feat_network package
-keep class com.mwkg.network.** { *; }

# Keep specific classes and their public members (if applicable)
-keep class com.mwkg.network.rest.HiAPIRequest { *; }
-keep class com.mwkg.network.rest.HiAPIResponse { *; }
-keep class com.mwkg.network.rest.HiEndpoint { *; }
-keep class com.mwkg.network.rest.HiNetworkManager { *; }
-keep class com.mwkg.network.socket.HiSocketManager { *; }
-keep class com.mwkg.network.websocket.HiWebSocketManager { *; }

# Keep all annotations in the library
-keepattributes *Annotation*

# Keep the method parameters and signatures
-keepattributes Signature, MethodParameters

# Preserve Serializable classes
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    private void readObjectNoData();
}