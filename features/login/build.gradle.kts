import deps.Dependencies
import deps.DependenciesVersions
import deps.androidx
import deps.dataModule
import deps.domainModule
import deps.navigatorModule
import deps.presentationModule
import deps.retrofit
import deps.room
import deps.testDebugDeps
import deps.testDeps
import deps.testImplDeps
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.android")
}
apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.multimodule.login"

    composeOptions {
        kotlinCompilerExtensionVersion = DependenciesVersions.KOTLIN_COMPILER
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(Dependencies.ANDROIDX_COMPOSE_BOM))
    androidx()
    room()
    testDeps()
    testImplDeps()
    testDebugDeps()
    domainModule()
    retrofit()
    dataModule()
    presentationModule()
    navigatorModule()

    implementation("com.google.dagger:hilt-android:2.54")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    ksp("com.google.dagger:hilt-android-compiler:2.53.1")
}
