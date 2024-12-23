
import dependencies.testDebugDeps
import dependencies.testDeps
import dependencies.testImplDeps
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
}
apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.multimodule.domain"
}

dependencies {
    testDeps()
    testImplDeps()
    testDebugDeps()
}
