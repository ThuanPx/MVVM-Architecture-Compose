plugins {
    `kotlin-dsl`
}

group = "com.thuanpx.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "android.application.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
        register("androidHilt") {
            id = "android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidDependency") {
            id = "android.dependency"
            implementationClass = "AndroidDependencyConventionPlugin"
        }
    }
}
