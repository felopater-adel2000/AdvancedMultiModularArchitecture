package plugs

import build.BuildConfig
import build.BuildCreator
import build.BuildDimension
import build.BuildTypes
import com.android.build.api.dsl.LibraryExtension
import falvors.BuildFlavor
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import siging.BuildSigning
import test.TestBuildConfig


class SharedLibraryGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.addPluginConfigurations()
        project.addAndroidConfigurations()
        project.applyKotlinOptions()
    }

    private fun Project.addPluginConfigurations() {
        plugins.apply(BuildPlugins.KOTLIN_ANDROID)
        plugins.apply(BuildPlugins.KAPT)
        plugins.apply(plugs.BuildPlugins.KOTLIN_COMPOSE)
        plugins.apply(plugs.BuildPlugins.KTLINT)
        plugins.apply(plugs.BuildPlugins.SPOTLESS)
        plugins.apply(plugs.BuildPlugins.DETEKT)
        plugins.apply(plugs.BuildPlugins.UPDATE_DEPS_VERSIONS)
    }

    private fun Project.addAndroidConfigurations() {
        extensions.getByType(LibraryExtension::class.java).apply {
            compileSdk = BuildConfig.COMPILE_SDK_VERSION

            defaultConfig {
                minSdk = BuildConfig.MIN_SDK_VERSION
                testInstrumentationRunner = TestBuildConfig.TEXT_INSTRUMENTATION_RUNNER
            }

            signingConfigs {
                BuildSigning.Release(project).create(this)
                BuildSigning.ReleaseExternalQa(project).create(this)
            }

            buildTypes {

                BuildCreator.Debug(project).createLibrary(this).apply {
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )

                    signingConfig = signingConfigs.getByName(BuildTypes.DEBUG)
                }

                BuildCreator.Release(project).createLibrary(this).apply {
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )

                    signingConfig = signingConfigs.getByName(BuildTypes.RELEASE)
                }

                BuildCreator.ReleaseExternalQa(project).createLibrary(this).apply {
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )

                    signingConfig = signingConfigs.getByName(BuildTypes.RELEASE_EXTERNAL_QA)
                }
            }

            flavorDimensions.add(BuildDimension.APP)
            flavorDimensions.add(BuildDimension.STORE)

            productFlavors {
                BuildFlavor.Google.createLibrary(this)
                BuildFlavor.Huawei.createLibrary(this)
                BuildFlavor.Client.createLibrary(this)
                BuildFlavor.Driver.createLibrary(this)
            }

            buildFeatures {
                compose = true
                buildConfig = true
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }
        }
    }

    private fun Project.applyKotlinOptions() {
        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.toString()
            }
        }
    }
}