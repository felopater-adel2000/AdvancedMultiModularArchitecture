import dependencies.Dependencies
import dependencies.androidx
import dependencies.hilt
import dependencies.loginModule
import dependencies.okHttp
import dependencies.retrofit
import dependencies.room
import dependencies.testDebugDeps
import dependencies.testDeps
import dependencies.testImplDeps

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
    id(plugs.BuildPlugins.KOTLIN_ANDROID)
    kotlin(plugs.BuildPlugins.KAPT)

}

android {
    namespace = "com.multimodule.login"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {

        create("releaseExternalQa") {
            
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(platform(Dependencies.ANDROIDX_COMPOSE_BOM))
    androidx()
    testDeps()
    testImplDeps()
    testDebugDeps()

    hilt()
    room()
}