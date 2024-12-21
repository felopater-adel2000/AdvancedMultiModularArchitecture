package plugs

import build.BuildConfig
import build.BuildDimension
import com.android.build.api.dsl.LibraryExtension
import falvors.BuildFlavor
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import test.TestBuildConfig


object SharedLibraryGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.addConfigurations()
        project.addAndroidConfiguration()
        project.applyKotlinOptions()
    }

    private fun Project.addConfigurations() {
        plugins.apply(plugs.BuildPlugins.KOTLIN_ANDROID)
        plugins.apply(plugs.BuildPlugins.KAPT)
    }

    private fun Project.addAndroidConfiguration() {
        extensions.getByType(LibraryExtension::class.java).apply {
            compileSdk = BuildConfig.COMPILE_SDK_VERSION

            defaultConfig {
                testInstrumentationRunner = TestBuildConfig.TEXT_INSTRUMENTATION_RUNNER
                minSdk = BuildConfig.MIN_SDK_VERSION
            }

            flavorDimensions.add(BuildDimension.APP)
            flavorDimensions.add(BuildDimension.STORE)


            productFlavors {
                BuildFlavor.Google.createLibrary(this)

                BuildFlavor.Huawei.createLibrary(this)

                BuildFlavor.Driver.createLibrary(this)

                BuildFlavor.Client.createLibrary(this)
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }

            buildFeatures {
                compose = true
                buildConfig = true
            }
        }
    }

    private fun Project.applyKotlinOptions() {
        tasks.withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.toString()
            }
        }
    }



}