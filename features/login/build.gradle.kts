import dependencies.Dependencies
import dependencies.androidx
import dependencies.hilt
import dependencies.room
import dependencies.testDebugDeps
import dependencies.testDeps
import dependencies.testImplDeps
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
}
apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.minafarid.login"
}

dependencies {
    implementation(platform(Dependencies.ANDROIDX_COMPOSE_BOM))
    androidx()
    hilt()
    room()
    testDeps()
    testImplDeps()
    testDebugDeps()
}