import deps.Dependencies
import deps.androidx
import deps.dataModule
import deps.hilt
import deps.retrofit
import deps.room
import deps.testDebugDeps
import deps.testDeps
import deps.testImplDeps
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
}
apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.multimodule.login"
}

dependencies {
    implementation(platform(Dependencies.ANDROIDX_COMPOSE_BOM))
    androidx()
    hilt()
    room()
    testDeps()
    testImplDeps()
    testDebugDeps()

    retrofit()
    dataModule()
}
