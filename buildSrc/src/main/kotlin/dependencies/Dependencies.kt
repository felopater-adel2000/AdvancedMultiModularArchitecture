package dependencies

object Dependencies {
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${DependenciesVersions.CORE_KTX}"
    const val ANDROIDX_LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${DependenciesVersions.LIFECYCLE_RUNTIME_KTX}"
    const val ANDROIDX_ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${DependenciesVersions.ACTIVITY_COMPOSE}"
    const val ANDROIDX_COMPOSE_BOM = "androidx.compose:compose-bom:${DependenciesVersions.COMPOSE_BOM}"
    const val ANDROIDX_UI = "androidx.compose.ui:ui"
    const val ANDROIDX_UI_GRAPHICS = "androidx.compose.ui:ui-graphics"
    const val ANDROIDX_UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview"
    const val ANDROIDX_MATERIAL3 = "androidx.compose.material3:material3"

    const val hiltAndroid = "com.google.dagger:hilt-android:${DependenciesVersions.HILT}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${DependenciesVersions.HILT}"
    const val hiltAgp = "com.google.dagger:hilt-android-gradle-plugin:${DependenciesVersions.HILT}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${DependenciesVersions.RETROFIT}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${DependenciesVersions.RETROFIT}"
    const val retrofitKotlinCoroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${DependenciesVersions.RETROFIT_COROUTINE_ADAPTER_VERSION}"

    const val okHttp = "com.squareup.okhttp3:okhttp:${DependenciesVersions.OKHTTP}"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${DependenciesVersions.OKHTTP}"

    const val roomRuntime = "androidx.room:room-runtime:${DependenciesVersions.ROOM}"
    const val roomCompiler = "androidx.room:room-compiler:${DependenciesVersions.ROOM}"
    const val roomKtx = "androidx.room:room-ktx:${DependenciesVersions.ROOM}"
}