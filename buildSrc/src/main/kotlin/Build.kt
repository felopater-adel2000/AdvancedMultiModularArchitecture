sealed class Build {

    open val isMinifyEnabled = false
    open val isDebuggable = false
    open val isTestCoverageEnabled = false
    open val versionNameSuffix = ""
    open val applicationIdSuffix = ""

    object Debug : Build() {
        override val versionNameSuffix = "-DEBUG"
        override val applicationIdSuffix = ".debug"

    }

    object ReleaseExternalQA : Build() {
        override val versionNameSuffix = "-RELEASE_EXTERNAL_QA"
        override val applicationIdSuffix = ".releaseExternalQa"

    }

    object Release: Build() {
    }
}