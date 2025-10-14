package com.example.composenavegacionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composenavegacionapp.screens.BudgetListScreen
import com.example.composenavegacionapp.ui.theme.ComposeNavegacionAppTheme
import com.example.composenavegacionapp.viewmodel.BudgetViewModel

class BudgetActivity : ComponentActivity() {
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

