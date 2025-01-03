import deps.Dependencies.protoBufArtifact
import deps.protoDataStore
import deps.protoDataStoreModule
import deps.testDebugDeps
import deps.testDeps
import deps.testImplDeps
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
    id(plugs.BuildPlugins.GOOGLE_PROTOBUF)
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
}

apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.multimodule.protodatasyore"

    protobuf {
        protoc {
            artifact = protoBufArtifact
        }
        generateProtoTasks {
            all().forEach { task ->
                task.plugins {
                    create("kotlin").apply {
                        option("lite")
                    }
                }
                task.plugins {
                    create("java").apply {
                        option("lite")
                    }
                }
            }
        }
    }

}

dependencies {
    testDeps()
    testImplDeps()
    testDebugDeps()
    protoDataStore()

    implementation("com.google.dagger:hilt-android:2.52")
    ksp("com.google.dagger:hilt-android-compiler:2.52")
}