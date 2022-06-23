plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

gradlePlugin {
    plugins {
        register("DependencyUpdatePlugin") {
            id = "dependency.update.plugin"
            implementationClass = "codequality.DependencyUpdatePlugin"
        }
    }
}

object Versions {
    const val versionChecker = "0.42.0"
    const val kotlin = "1.6.21"
    const val gradle = "7.2.1"
    const val hilt = "2.42"
}

object Deps {
    const val versionChecker = "com.github.ben-manes:gradle-versions-plugin:${Versions.versionChecker}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
}

dependencies {
    implementation(Deps.versionChecker)
    implementation(Deps.kotlin)
    implementation(Deps.gradle)
    implementation(Deps.hilt)
}