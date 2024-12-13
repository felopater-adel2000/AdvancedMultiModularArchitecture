import org.gradle.api.Project
import java.util.Properties

private const val LOCAL_PROPERTIES = "dev_credentials"

fun Project.getLocalProperty(propertyName: String): String
{
    val localProperties = Properties().apply {
        val localPropertyFile = project.rootProject.file(LOCAL_PROPERTIES)

        if(localPropertyFile.exists())
        {
            load(localPropertyFile.inputStream())
        }
    }

    return localProperties.getProperty(propertyName)
}
