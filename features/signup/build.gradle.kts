import deps.androidx
import deps.navigatorModule
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
    namespace = "com.multimodule.signup"
}

dependencies {
    androidx()
    testDeps()
    testImplDeps()
    testDebugDeps()
    navigatorModule()
    implementation("com.google.dagger:hilt-android:2.54")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    ksp("com.google.dagger:hilt-android-compiler:2.53.1")
}


