package com.thuanpx.buildsrc

object Versions {
    const val gradle = "7.2.1"
    const val kotlin = "1.7.0"
    const val versionChecker = "0.42.0"
    const val compose = "1.2.0-beta02"
    const val activityCompose = "1.4.0"
    const val coreKtx = "1.8.0"
    const val appCompat = "1.4.1"
    const val material = "1.7.0-alpha02"
    const val lifecycle = "2.4.1"
    const val accompanist = "0.24.11-rc"
    const val coil = "2.1.0"
    const val coroutines = "1.6.3"
    const val okhttp = "5.0.0-alpha.9"
    const val retrofit = "2.9.0"
    const val timer = "5.0.1"
    const val gson = "2.9.0"
    const val hilt = "2.42"
    const val splash = "1.0.0-beta02"
    const val palette = "1.0.0"
}

object ClassPath {
    val gradle by lazy { "com.android.tools.build:gradle:${Versions.gradle}" }
    val kotlin by lazy { "org.jetbrains.java:java-gradle-plugin:${Versions.kotlin}" }
    val hilt by lazy { "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}" }
}

object OtherLib {
    val timber by lazy { "com.jakewharton.timber:timber:${Versions.timer}" }
    val gson by lazy { "com.google.code.gson:gson:${Versions.gson}" }
    val versionChecker by lazy { "com.github.ben-manes:gradle-versions-plugin:${Versions.versionChecker}" }
    val appcompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val splashScreen by lazy { "androidx.core:core-splashscreen:${Versions.splash}" }
    val palette by lazy { "androidx.palette:palette-ktx:${Versions.palette}" }
}

object SupportLib {
    const val CoreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
}

object LifecycleLib {
    const val ViewModelCompose =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}"
    const val LifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
}

object ComposeLib {
    const val ui = "androidx.compose.ui:ui:1.1.1"
    const val uiUtil = "androidx.compose.ui:ui-util:1.2.0-rc01"
    const val preview = "androidx.compose.ui:ui-tooling-preview:1.1.1"
    const val runtime = "androidx.compose.runtime:runtime:1.1.1"
    const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata:1.1.1"
    const val foundation = "androidx.compose.foundation:foundation:1.1.1"
    const val foundationLayout = "androidx.compose.foundation:foundation-layout:1.1.1"
    const val tooling = "androidx.compose.ui:ui-tooling:1.1.1"
    const val manifest = "androidx.compose.ui:ui-test-manifest:1.1.1"
    const val animation = "androidx.compose.animation:animation:1.2.0-rc01"
    const val material3 = "androidx.compose.material3:material3:1.0.0-alpha13"

    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.0"
    const val paging = "androidx.paging:paging-compose:1.0.0-alpha14"
}

object CoilLib {
    val coil by lazy { "io.coil-kt:coil-compose:${Versions.coil}" }
    val coilSvg by lazy { "io.coil-kt:coil-svg:${Versions.coil}" }
}

object CoroutinesLib {
    val coroutinesCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}" }
    val coroutinesAndroid by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}" }
}

object RetrofitLib {
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val retrofitConverterGson by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit}" }
}

object OkhttpLib {
    val okhttp by lazy { "com.squareup.okhttp3:okhttp:${Versions.okhttp}" }
    val okhttpLogging by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}" }
}

object MaterialLib {
    const val material = "com.google.android.material:material:${Versions.material}"
}

object NavigationLib {
    const val navigation = "androidx.navigation:navigation-compose:2.4.2"
    const val destinationCore = "io.github.raamcosta.compose-destinations:core:1.4.4-beta"
    const val destinationKsp = "io.github.raamcosta.compose-destinations:ksp:1.4.4-beta"
    const val destinationAnimation =
        "io.github.raamcosta.compose-destinations:animations-core:1.4.4-beta"
}

object AccompanistLib {
    const val swipeRefresh =
        "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanist}"
    const val systemuicontroller = "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
    const val insets = "com.google.accompanist:accompanist-insets:${Versions.accompanist}"
    const val placeholderMaterial = "com.google.accompanist:accompanist-placeholder-material:${Versions.accompanist}"
    const val navigationMaterial = "com.google.accompanist:accompanist-navigation-material:${Versions.accompanist}"
    const val permissions = "com.google.accompanist:accompanist-permissions:${Versions.accompanist}"
    const val pager = "com.google.accompanist:accompanist-pager:${Versions.accompanist}"
    const val indicators = "com.google.accompanist:accompanist-pager-indicators:${Versions.accompanist}"
    const val webview = "com.google.accompanist:accompanist-webview:0.24.6-alpha"
}

object StorageLib {
    const val roomKtx = "androidx.room:room-ktx:2.4.2"
    const val roomCompiler = "androidx.room:room-compiler:2.4.2"
    const val datastorePref = "androidx.datastore:datastore-preferences:1.0.0"
    const val datastore = "androidx.datastore:datastore:1.0.0"
    const val securityPref = "androidx.security:security-crypto-ktx:1.1.0-alpha03"
}

object HiltLib {
    const val android = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val compose = "androidx.hilt:hilt-navigation-compose:1.0.0"
}
