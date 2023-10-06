plugins {
    id("android.application")
    id("android.application.compose")
    id("android.hilt")
    id("android.dependency")
}

android {
    namespace = "com.thuanpx.mvvm_compose"

    defaultConfig {
        applicationId = "com.thuanpx.mvvm_compose"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_API_URL", "\"https://pokeapi.co/api/v2/\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                file("proguard-rules.pro")
            )
            buildConfigField("String", "BASE_API_URL", "\"https://pokeapi.co/api/v2/\"")
        }
      create("benchmark") {
        signingConfig = signingConfigs.getByName("debug")
        matchingFallbacks += listOf("release")
        isDebuggable = false
      }
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.accompanist.systemuicontroller)

    implementation(libs.androidx.dataStore.preferences)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.okhttp.logging)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.paging.runtime)
    debugImplementation(libs.chucker.library)
    releaseImplementation(libs.chucker.library.no.op)
}