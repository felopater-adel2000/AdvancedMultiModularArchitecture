
import deps.chucker
import deps.dataStore
import deps.domainModule
import deps.okHttp
import deps.protoDataStore
import deps.protoDataStoreModule
import deps.retrofit
import deps.testDebugDeps
import deps.testDeps
import deps.testImplDeps
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}
apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.multimodule.data"
}

dependencies {
    testDeps()
    testImplDeps()
    testDebugDeps()
    chucker()
    okHttp()
    retrofit()
    dataStore()
    protoDataStoreModule()
    domainModule()

    //Hilt Dependency
    implementation("com.google.dagger:hilt-android:2.52")
    ksp("com.google.dagger:hilt-android-compiler:2.52")

}
