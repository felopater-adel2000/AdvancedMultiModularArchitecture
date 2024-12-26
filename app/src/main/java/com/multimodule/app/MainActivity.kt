package com.multimodule.app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.lifecycle.lifecycleScope
import com.multimodule.app.ui.theme.AdvancedMultiModularArchitectureTheme
import com.multimodule.datastore.settings.AppSettings
import com.multimodule.datastore.settings.AppSettingsSerializer
import com.multimodule.datastore.settings.Language
import com.multimodule.datastore.settings.Location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    lateinit var appSettingsDataStore: DataStore<AppSettings>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appSettingsDataStore = DataStoreFactory.create(
            serializer = AppSettingsSerializer(),
            produceFile = { dataStoreFile("app_settings.json") },
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
        )

        enableEdgeToEdge()
        setContent {
            AdvancedMultiModularArchitectureTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        modifier = Modifier.padding(innerPadding),
//                    )
                    SettingsScreen(appSettingsDataStore, modifier = Modifier.padding(innerPadding))
                }

            }
        }
    }
}

@Composable
fun SettingsScreen(appSettingsDataStore: DataStore<AppSettings>, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val appSettings by appSettingsDataStore.data.collectAsState(initial = AppSettings())

    Column(modifier = Modifier.padding(50.dp)) {
        Text(text = "Language: " + appSettings.language)
        val newLocation = Location(37.4221, -122.0841)
        DropdownMenu(
            expanded = true,
            onDismissRequest = {},
        ) {
            Language.values().forEach { language ->
                DropdownMenuItem(text = { Text(text = language.name) }, onClick = {
                    scope.launch {
                        appSettingsDataStore.updateData { currentSettings ->
                            currentSettings.copy(
                                language = language,
                                lastKnownLocations2 = currentSettings.lastKnownLocations2.add(
                                    newLocation,
                                ),
                            )
                        }
                    }
                })
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Last Known Locations:")

        appSettings.lastKnownLocations2.forEach { location ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Lat: ${location.lat}, Long: ${location.long}")
        }
    }
}

@Composable
@Suppress("FunctionNaming")
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.fillMaxSize(),
        text = "BASE_URL = $name",
        color = Color.Red,
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,

    )
}

@Preview(showBackground = true)
@Composable
@Suppress("FunctionNaming")
fun GreetingPreview() {
    AdvancedMultiModularArchitectureTheme {
        Greeting(BuildConfig.BASE_URL)
    }
}
