
import deps.kotlinx
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
    namespace = "com.multimodule.domain"
}

dependencies {
    kotlinx()
    testDeps()
    testImplDeps()
    testDebugDeps()

    implementation("com.google.dagger:hilt-android:2.52")
    ksp("com.google.dagger:hilt-android-compiler:2.52")
}
