package com.multimodule.app

import android.os.Bundle
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
import com.multimodule.app.ui.theme.AdvancedMultiModularArchitectureTheme

class MainActivity : ComponentActivity() {
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
    }

    private fun test() {
        val counter = 100
    }

    fun mainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMain() {}
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
