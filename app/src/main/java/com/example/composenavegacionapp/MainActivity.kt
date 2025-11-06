package com.example.composenavegacionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composenavegacionapp.ui.theme.ComposeNavegacionAppTheme
import com.example.composenavegacionapp.navigation.AppNavHost
import com.example.composenavegacionapp.viewmodel.BudgetViewModel
import com.example.composenavegacionapp.viewmodel.TransactionViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.isSystemInDarkTheme
import com.example.composenavegacionapp.data.ThemePreferences

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val isDark by ThemePreferences.darkModeFlow(context).collectAsState(initial = isSystemInDarkTheme())

            ComposeNavegacionAppTheme(darkTheme = isDark) {
                val budgetViewModel: BudgetViewModel = viewModel()
                val transactionViewModel: TransactionViewModel = viewModel()
                AppNavHost(
                    budgetViewModel = budgetViewModel,
                    transactionViewModel = transactionViewModel
                )
            }
        }
    }
}
