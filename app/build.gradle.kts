import com.thuanpx.buildsrc.*

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("kotlin-android")
    id("kotlin-parcelize")
}

apply<com.thuanpx.codequality.DependencyUpdatePlugin>()

android {
    namespace = "com.thuanpx.mvvm_architecture_compose"
    compileSdk = Config.compileSdk
    buildToolsVersion = Config.buildToolsVersion
    flavorDimensions += Config.flavorDimensions

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                file("proguard-rules.pro")
            )
            buildConfigField("String", "END_POINT", "\"${Config.Release.BaseUrl}\"")

        }
        debug {
            applicationIdSuffix = ".dev"
            buildConfigField("String", "END_POINT", "\"${Config.Debug.BaseUrl}\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        freeCompilerArgs = Config.freeCompilerArgs
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    packagingOptions {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(SupportLib.CoreKtx)

    implementation(LifecycleLib.ViewModelCompose)
    implementation(LifecycleLib.LifecycleRuntime)

    implementation(ComposeLib.activityCompose)
    implementation(ComposeLib.runtime)
    implementation(ComposeLib.runtimeLiveData)
    implementation(ComposeLib.animation)
    implementation(ComposeLib.constraintLayout)
    implementation(ComposeLib.ui)
    implementation(ComposeLib.uiUtil)
    implementation(ComposeLib.preview)
    implementation(ComposeLib.foundation)
    implementation(ComposeLib.foundationLayout)
    implementation(ComposeLib.material3)
    implementation(ComposeLib.paging)
    debugImplementation(ComposeLib.tooling)
    debugImplementation(ComposeLib.manifest)

    implementation(AccompanistLib.swipeRefresh)

    implementation(MaterialLib.material)

    implementation(NavigationLib.navigation)

    implementation(CoilLib.coil)
    implementation(CoilLib.coilSvg)

    implementation(HiltLib.android)
    implementation(HiltLib.compose)
    kapt(HiltLib.compiler)

    implementation(CoroutinesLib.coroutinesCore)
    implementation(CoroutinesLib.coroutinesAndroid)

    implementation(RetrofitLib.retrofit)
    implementation(RetrofitLib.retrofitConverterGson)

    implementation(OkhttpLib.okhttp)
    implementation(OkhttpLib.okhttpLogging)

    implementation(StorageLib.datastorePref)

    implementation(OtherLib.timber)
    implementation(OtherLib.gson)
    implementation(OtherLib.splashScreen)
}
