# --- General ProGuard rules for feat_nfc module ---

# Keep all public classes and methods in the feat_nfc package
-keep public class com.mwkg.network.** { *; }

# Preserve annotations
-keepattributes *Annotation*

# Preserve method signatures for reflection
-keepattributes Signature, MethodParameters, EnclosingMethod, InnerClasses

# Preserve Serializable classes
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    private void readObjectNoData();
}

# --- Rules for java.lang.invoke package and StringConcatFactory ---

# Suppress warnings for missing StringConcatFactory
-dontwarn java.lang.invoke.StringConcatFactory

# Keep all classes from java.lang.invoke package
-keep class java.lang.invoke.** { *; }

# Explicitly keep StringConcatFactory
#-keep class java.lang.invoke.StringConcatFactory { *; }

# --- Specific ProGuard rules for feat_nfc classes ---

# Keep HiAPIRequest class and its public members
-keep class com.mwkg.network.rest.HiAPIRequest { *; }

# Keep HiAPIResponse class and its public members
-keep class com.mwkg.network.rest.HiAPIResponse { *; }

# Keep HiEndpoint class and its public methods
-keep class com.mwkg.network.rest.HiEndpoint { *; }

# Keep HiNetworkManager class and its public methods
-keep class com.mwkg.network.rest.HiNetworkManager { *; }

# Keep HiSocketManager class and its public methods
-keep class com.mwkg.network.socket.HiSocketManager { *; }

# Keep HiWebSocketManager class and its public members
-keep class com.mwkg.network.websocket.HiWebSocketManager { *; }

# --- Additional generic rules for safety ---

# Keep Parcelable implementations
-keepclassmembers class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
}

# Keep Data Binding generated classes
-keep class androidx.databinding.** { *; }
-keepclassmembers class androidx.databinding.** { *; }

# Keep Jetpack Compose-related classes
-keep class androidx.compose.** { *; }
-keep class kotlin.Unit { *; }

# Keep coroutines-related classes
-keep class kotlinx.coroutines.** { *; }

# Keep Gson models (if applicable)
-keep class com.google.gson.** { *; }
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}