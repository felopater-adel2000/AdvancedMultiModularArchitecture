
import deps.testDebugDeps
import deps.testDeps
import deps.testImplDeps
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
}
apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.multimodule.presentation"
}

dependencies {
    testDeps()
    testImplDeps()
    testDebugDeps()
}
