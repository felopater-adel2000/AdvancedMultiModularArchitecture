package falvors

import build.BuildDimension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.LibraryProductFlavor
import org.gradle.api.NamedDomainObjectContainer

sealed class BuildFlavor(val name: String) {

    // its used for app
    abstract fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>
    ) : ApplicationProductFlavor

    // its used for modules
    abstract fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>
    ) : LibraryProductFlavor


    object Google : BuildFlavor(FlavorTypes.GOOGLE){
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor {
            return namedDomainObjectContainer.create(name){
                dimension = BuildDimension.STORE
                applicationIdSuffix = ".$name"
                versionNameSuffix = "-$name"
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor {
            return namedDomainObjectContainer.create(name){
                dimension = BuildDimension.STORE
            }
        }
    }

    object Huawei : BuildFlavor(FlavorTypes.HUAWEI){
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor {
            return namedDomainObjectContainer.create(name){
                dimension = BuildDimension.STORE
                applicationIdSuffix = ".$name"
                versionNameSuffix = "-$name"
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor {
            return namedDomainObjectContainer.create(name){
                dimension = BuildDimension.STORE
            }
        }
    }

    object Driver : BuildFlavor(FlavorTypes.DRIVER){
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor {
            return namedDomainObjectContainer.create(name){
                dimension = BuildDimension.APP
                applicationIdSuffix = ".$name"
                versionNameSuffix = "-$name"
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor {
            return namedDomainObjectContainer.create(name){
                dimension = BuildDimension.APP
            }
        }
    }

    object Client : BuildFlavor(FlavorTypes.CLIENT){
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor {
            return namedDomainObjectContainer.create(name){
                dimension = BuildDimension.APP
                applicationIdSuffix = ".$name"
                versionNameSuffix = "-$name"
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor {
            return namedDomainObjectContainer.create(name){
                dimension = BuildDimension.APP
            }
        }
    }


}