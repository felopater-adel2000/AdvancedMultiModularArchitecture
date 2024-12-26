package com.multimodule.app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.lifecycle.lifecycleScope
import com.multimodule.app.ui.theme.AdvancedMultiModularArchitectureTheme
import com.multimodule.datastore.settings.AppSettings
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    lateinit var appSettingDataStore: DataStore<AppSettings>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdvancedMultiModularArchitectureTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "${BuildConfig.BASE_URL} ${MapProvider.MAP_ID}",
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }

        val counter = 100


        lifecycleScope.launch {
            val response = async { getString() }

            Log.d("Felo", "onCreate: after async")

            val result = response.await()

            Log.d("Felo", "onCreate: after await $result")


        }

    }


    suspend fun getString(): String {
        delay(10000)
        return "Hello"
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
