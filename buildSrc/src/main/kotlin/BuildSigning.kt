import com.android.build.api.dsl.ApkSigningConfig
import com.android.build.api.dsl.ApplicationProductFlavor
import org.gradle.api.NamedDomainObjectContainer
import java.io.File

sealed class BuildSigning(val name: String) {

    abstract fun create(action: NamedDomainObjectContainer<out ApkSigningConfig>)

    object  Release : BuildSigning(SigningTypes.RELEASE) {
        override fun create(action: NamedDomainObjectContainer<out ApkSigningConfig>) {
            action.create(name) {
                storeFile = File("path/release/myKeyStore.jks")
                storePassword = "password"
                keyAlias = "myKeyAlias"
                keyPassword = "password"
                enableV1Signing = true
                enableV2Signing = true
            }
        }
    }

    object  ReleaseExternalQa : BuildSigning(SigningTypes.RELEASE_EXTERNAL_QA) {
        override fun create(action: NamedDomainObjectContainer<out ApkSigningConfig>) {
            action.create(name) {
                storeFile = File("path/release/myKeyStore.jks")
                storePassword = "password"
                keyAlias = "myKeyAlias"
                keyPassword = "password"
                enableV1Signing = true
                enableV2Signing = true
            }
        }
    }

    object  Debug : BuildSigning(SigningTypes.DEBUG) {
        override fun create(action: NamedDomainObjectContainer<out ApkSigningConfig>) {
            action.getByName(name) {
                storeFile = File("path/release/myKeyStore.jks")
                storePassword = "password"
                keyAlias = "myKeyAlias"
                keyPassword = "password"
                enableV1Signing = true
                enableV2Signing = true
            }
        }
    }

}