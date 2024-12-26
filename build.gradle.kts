plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    `maven-publish` // Maven publishing for JitPack
}

android {
    namespace = "com.mwkg.network"
    compileSdk = 35

    defaultConfig {
        minSdk = 31

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {
    // AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Retrofit for RESTful API
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // OkHttp for WebSocket
    implementation(libs.okhttp)

    // Coroutine support
    implementation(libs.kotlinx.coroutines.core)

    // ViewModel KTX for viewModelScope
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Kotlin Serialization
    implementation(libs.kotlinx.serialization.json)
}

// Maven publishing configuration for JitPack
publishing {
    publications {
        create<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
            }

            // Define Maven artifact metadata
            groupId = "com.github.clodymade"    // GitHub username as group ID
            artifactId = "feat_network"             // Module name as artifact ID
            version = "1.0.0"                   // Version matching the Git tag

            // Configure POM metadata
            pom {
                name.set("feat_network")
                description.set("A utility library for Android apps.")
                url.set("https://github.com/clodymade/feat_network")

                licenses {
                    license {
                        name.set("MIT License") // License type
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                developers {
                    developer {
                        id.set("clodymade")
                        name.set("netcanis")
                        email.set("netcanis@gmail.com")
                    }
                }

                scm {
                    // Source Control Management (SCM) details
                    connection.set("scm:git:git://github.com/clodymade/feat_network.git")
                    developerConnection.set("scm:git:ssh://git@github.com:clodymade/feat_network.git")
                    url.set("https://github.com/clodymade/feat_network")
                }
            }
        }
    }
}
