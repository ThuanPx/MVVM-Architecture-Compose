plugins {
//    id("org.jetbrains.kotlin.android")
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("kotlin-android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.thuanpx.mvvm_architecture_compose"
    compileSdk = 32
    buildToolsVersion = "30.0.3"
    flavorDimensions += "default"

    defaultConfig {
        applicationId = "com.thuanpx.mvvm_architecture_compose"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    productFlavors {
        create("dev") {
            applicationIdSuffix = ".dev"
            versionCode = 1
            versionName = "1.0.0"

            buildConfigField("String", "END_POINT", "\"https://pokeapi.co/api/v2/\"")
        }

        create("prod") {
            versionCode = 1
            versionName = "1.0.0"

            buildConfigField("String", "END_POINT", "\"https://pokeapi.co/api/v2/\"")
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                file("proguard-rules.pro")
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            // Enable experimental coroutines APIs, including Flow
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xopt-in=kotlinx.coroutines.FlowPreview",
            "-Xopt-in=kotlin.Experimental",
            "-Xopt-in=ExperimentalMaterial3Api",
            // Enable experimental kotlinx serialization APIs
            "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi"
        )

        // Set JVM target to 1.8
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }
    packagingOptions {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation ("androidx.activity:activity-compose:1.4.0")

    // Compose
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("androidx.compose.runtime:runtime:1.2.0-beta02")
    implementation("androidx.compose.runtime:runtime-livedata:1.2.0-beta02")
    implementation ("androidx.compose.ui:ui:1.1.1")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.1.1")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.1.1")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.1.1")
    implementation("androidx.compose.foundation:foundation:1.2.0-beta02")
    // Material
    implementation ("androidx.compose.material3:material3:1.0.0-alpha12")
    implementation("com.google.android.material:material:1.7.0-alpha02")
    // Navigation
    implementation ("androidx.navigation:navigation-compose:2.4.2")

    // Coil
    implementation("io.coil-kt:coil-compose:2.1.0")
    implementation("io.coil-kt:coil-svg:2.1.0")
    // Hilt
    implementation("com.google.dagger:hilt-android:2.42")
    kapt("com.google.dagger:hilt-android-compiler:2.42")
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // Okhttp
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    // Gson
    implementation("com.google.code.gson:gson:2.9.0")
    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")
    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")
}