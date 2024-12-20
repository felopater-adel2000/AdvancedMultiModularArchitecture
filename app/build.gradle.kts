import build.BuildConfig
import build.BuildCreator
import build.BuildDimension
import build.BuildTypes
import dependencies.Dependencies
import falvors.BuildFlavor
import release.ReleaseConfig
import siging.BuildSigning
import test.TestBuildConfig

plugins {

    id(plugs.BuildPlugins.ANDROID_APPLICATION)
    id(plugs.BuildPlugins.KOTLIN_ANDROID)
    id(plugs.BuildPlugins.KOTLIN_COMPOSE)
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
                "proguard-rules.pro"
            )

            signingConfig = signingConfigs.getByName(BuildTypes.DEBUG)
        }

        BuildCreator.Release(project).create(this).apply {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            signingConfig = signingConfigs.getByName(BuildTypes.RELEASE)
        }

        BuildCreator.ReleaseExternalQa(project).create(this).apply {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            signingConfig = signingConfigs.getByName(BuildTypes.RELEASE_EXTERNAL_QA)
        }

        /*getByName(build.BuildTypes.RELEASE) {
            isMinifyEnabled = build.Build.Release.isMinifyEnabled
            signingConfig = signingConfigs.getByName(build.BuildTypes.RELEASE)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName(build.BuildTypes.DEBUG) {
            isMinifyEnabled = build.Build.Debug.isMinifyEnabled
            versionNameSuffix = build.Build.Debug.versionNameSuffix
            applicationIdSuffix = build.Build.Debug.applicationIdSuffix
            signingConfig = signingConfigs.getByName(build.BuildTypes.DEBUG)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        create(build.BuildTypes.RELEASE_EXTERNAL_QA) {
            isMinifyEnabled = build.Build.ReleaseExternalQA.isMinifyEnabled
            versionNameSuffix = build.Build.ReleaseExternalQA.versionNameSuffix
            applicationIdSuffix = build.Build.ReleaseExternalQA.applicationIdSuffix
            //signingConfig = signingConfigs.getByName(build.BuildTypes.RELEASE_EXTERNAL_QA)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }*/
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

    implementation(Dependencies.ANDROIDX_CORE_KTX)
    implementation(Dependencies.ANDROIDX_LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.ANDROIDX_ACTIVITY_COMPOSE)
    implementation(platform(Dependencies.ANDROIDX_COMPOSE_BOM))
    implementation(Dependencies.ANDROIDX_UI)
    implementation(Dependencies.ANDROIDX_UI_GRAPHICS)
    implementation(Dependencies.ANDROIDX_UI_TOOLING_PREVIEW)
    implementation(Dependencies.ANDROIDX_MATERIAL3)


    testImplementation(TestDependencies.JUNIT)
    androidTestImplementation(TestDependencies.ANDROIDX_JUNIT)
    androidTestImplementation(TestDependencies.ANDROIDX_ESPRESSO_CORE)
    androidTestImplementation(platform(Dependencies.ANDROIDX_COMPOSE_BOM))
    androidTestImplementation(TestDependencies.ANDROIDX_UI_TEST_JUNIT4)
    debugImplementation(TestDependencies.ANDROIDX_UI_TOOLING)
    debugImplementation(TestDependencies.ANDROIDX_UI_TEST_MANIFEST)
}