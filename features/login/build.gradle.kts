import deps.Dependencies
import deps.androidx
import deps.dataModule
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
}
apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.multimodule.login"
}

dependencies {
    implementation(platform(Dependencies.ANDROIDX_COMPOSE_BOM))
    androidx()
    room()
    testDeps()
    testImplDeps()
    testDebugDeps()

    retrofit()
    dataModule()

    implementation("com.google.dagger:hilt-android:2.52")
    ksp("com.google.dagger:hilt-android-compiler:2.52")
}
