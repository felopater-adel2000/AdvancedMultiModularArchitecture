import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.LibraryProductFlavor
import org.gradle.api.NamedDomainObjectContainer

sealed class BuildFlavor(val name: String) {

    abstract fun create(action: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor

    abstract fun createLibrary(action: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor
}