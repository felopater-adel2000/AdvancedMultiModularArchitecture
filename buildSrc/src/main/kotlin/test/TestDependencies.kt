package test

import dependencies.DependenciesVersions

object TestDependencies {
    const val JUNIT = "junit:junit:${DependenciesVersions.JUNIT}"
    const val ANDROIDX_JUNIT = "androidx.test.ext:junit:${DependenciesVersions.JUNIT_VERSION}"
    const val ANDROIDX_ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${DependenciesVersions.ESPRESSO_CORE}"
    const val ANDROIDX_UI_TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest"
    const val ANDROIDX_UI_TEST_JUNIT4 = "androidx.compose.ui:ui-test-junit4"
    const val ANDROIDX_UI_TOOLING = "androidx.compose.ui:ui-tooling"
}