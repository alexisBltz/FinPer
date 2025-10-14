package com.example.composenavegacionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composenavegacionapp.screens.BudgetListScreen
import com.example.composenavegacionapp.ui.theme.ComposeNavegacionAppTheme
import com.example.composenavegacionapp.viewmodel.BudgetViewModel

/**
 * Activity de ejemplo para la funcionalidad de presupuestos.
 *
 * Para probar esta pantalla, reemplaza temporalmente el contenido
 * de onCreate() en MainActivity.kt con este código:
 *
 * setContent {
 *     ComposeNavegacionAppTheme {
 *         val viewModel: BudgetViewModel = viewModel()
 *         BudgetListScreen(viewModel = viewModel)
 *     }
 * }
 */
class MainActivityPresupuestos : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeNavegacionAppTheme {
                val viewModel: BudgetViewModel = viewModel()
                BudgetListScreen(viewModel = viewModel)
            }
        }
    }
}

