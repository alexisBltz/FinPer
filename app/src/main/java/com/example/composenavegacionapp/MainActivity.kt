@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.composenavegacionapp
package com.example.composenavegacionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.composenavegacionapp.ui.theme.ComposeNavegacionAppTheme
import com.example.composenavegacionapp.navigation.AppNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeNavegacionAppTheme {
                AppNavHost()
            }
        }
    }
}
import androidx.compose.ui.text.font.FontWeight
