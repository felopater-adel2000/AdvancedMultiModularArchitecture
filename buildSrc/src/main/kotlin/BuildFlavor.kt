import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.LibraryProductFlavor
import org.gradle.api.NamedDomainObjectContainer

sealed class BuildFlavor(val name: String) {

    abstract fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor

    abstract fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor

    object Google : BuildFlavor(FlavorTypes.GOOGLE) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor {
            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimension.STORE
                applicationIdSuffix = ".$name"
                versionNameSuffix = "-$name"
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor {
            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimension.STORE
            }
        }
    }

    object Huawei : BuildFlavor(FlavorTypes.HUAWEI) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor {
            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimension.STORE
                applicationIdSuffix = ".$name"
                versionNameSuffix = "-$name"
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor {
            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimension.STORE
            }
        }
    }

    object Driver : BuildFlavor(FlavorTypes.DRIVER) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor {
            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimension.APP
                applicationIdSuffix = ".$name"
                versionNameSuffix = "-$name"
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor {
            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimension.STORE
            }
        }
    }

    object Client : BuildFlavor(FlavorTypes.CLIENT) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor {
            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimension.APP
                applicationIdSuffix = ".$name"
                versionNameSuffix = "-$name"
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor {
            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimension.STORE
            }
        }
    }
}