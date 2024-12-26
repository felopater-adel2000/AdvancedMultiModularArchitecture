import build.BuildConfig
import build.BuildCreator
import build.BuildDimension
import build.BuildTypes
import deps.Dependencies
import deps.DependenciesVersions
import deps.androidx
import deps.dataModule
import deps.dataStore
import deps.dataStoreModule
import deps.domainModule
import deps.hilt
import deps.loginModule
import deps.okHttp
import deps.presentationModule
import deps.retrofit
import deps.room
import deps.testDebugDeps
import deps.testDeps
import deps.testImplDeps
import falvors.BuildFlavor
import release.ReleaseConfig
import siging.BuildSigning
import test.TestBuildConfig

plugins {
    id(plugs.BuildPlugins.ANDROID_APPLICATION)
    id(plugs.BuildPlugins.KOTLIN_ANDROID)
    id(plugs.BuildPlugins.KOTLIN_COMPOSE)
    id(plugs.BuildPlugins.ANDROID)
    id(plugs.BuildPlugins.KAPT)
    /*id(plugs.BuildPlugins.KTLINT)
    id(plugs.BuildPlugins.SPOTLESS)
    id(plugs.BuildPlugins.DETEKT)
    id(plugs.BuildPlugins.UPDATE_DEPS_VERSIONS)
    id(plugs.BuildPlugins.DOKKA)*/
    id(plugs.BuildPlugins.HILT) version deps.DependenciesVersions.HILT
}

android {
    namespace = BuildConfig.APP_ID
    compileSdk = BuildConfig.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = BuildConfig.APP_ID
        minSdk = BuildConfig.MIN_SDK_VERSION
        targetSdk = BuildConfig.TARGET_SDK_VERSION
        versionCode = ReleaseConfig.VERSION_CODE
        versionName = ReleaseConfig.VERSION_NAME

        testInstrumentationRunner = TestBuildConfig.TEXT_INSTRUMENTATION_RUNNER
    }

    signingConfigs {
        BuildSigning.Release(project).create(this)
        BuildSigning.ReleaseExternalQa(project).create(this)
    }

    buildTypes {

        BuildCreator.Debug(project).create(this).apply {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )

            signingConfig = signingConfigs.getByName(BuildTypes.DEBUG)
        }

        BuildCreator.Release(project).create(this).apply {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )

            signingConfig = signingConfigs.getByName(BuildTypes.RELEASE)
        }

        BuildCreator.ReleaseExternalQa(project).create(this).apply {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )

            signingConfig = signingConfigs.getByName(BuildTypes.RELEASE_EXTERNAL_QA)
        }
    }

    flavorDimensions.add(BuildDimension.APP)
    flavorDimensions.add(BuildDimension.STORE)

    productFlavors {
        BuildFlavor.Google.create(this)

        BuildFlavor.Huawei.create(this)

        BuildFlavor.Driver.create(this)

        BuildFlavor.Client.create(this)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(platform(Dependencies.ANDROIDX_COMPOSE_BOM))
    loginModule()
    dataModule()
    dataStoreModule()
    dataStore()
    domainModule()
    presentationModule()
    androidx()
    hilt()
    room()
    okHttp()
    retrofit()
    testDeps()
    testImplDeps()
    testDebugDeps()

}
