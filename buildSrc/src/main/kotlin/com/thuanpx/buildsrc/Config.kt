package com.thuanpx.buildsrc

object Config {
    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0

    private fun generateVersionCode(): Int {
        return versionMajor * 10000 + versionMinor * 100 + versionPatch
    }

    private fun generateVersionName(): String {
        return "$versionMajor.$versionMinor.$versionPatch"
    }

    val versionCode = generateVersionCode()
    val versionName = generateVersionName()
    const val applicationId = "com.thuanpx.mvvm_architecture_compose"
    const val minSdk = 24
    const val targetSdk = 32
    const val compileSdk = 32
    const val buildToolsVersion = "30.0.3"
    const val flavorDimensions = "default"
    val freeCompilerArgs = listOf(
        "-Xjvm-default=all",
        "-opt-in=java.RequiresOptIn",
        "-opt-in=java.Experimental",
        "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        "-opt-in=kotlinx.coroutines.InternalCoroutinesApi",
        "-opt-in=kotlinx.coroutines.FlowPreview",
        "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
        "-opt-in=com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi",
        "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
        "-opt-in=ExperimentalMaterial3Api",
        "-opt-in=kotlinx.serialization.ExperimentalSerializationApi",
        "-opt-in=kotlin.RequiresOptIn"
    )

    object Release {
        const val BaseUrl = "https://pokeapi.co/api/v2/"
    }

    object Debug {
        const val BaseUrl = "https://pokeapi.co/api/v2/"
    }
}