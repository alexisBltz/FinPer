package com.example.composenavegacionapp.navigation

import androidx.compose.runtime.*
import com.example.composenavegacionapp.screens.*
import com.example.composenavegacionapp.viewmodel.BudgetViewModel
import com.example.composenavegacionapp.viewmodel.TransactionViewModel

sealed class Screen {
    object Splash : Screen()
    object Home : Screen()
    data class Details(val data: String) : Screen()
    object BudgetForm : Screen()
}

@Composable
fun AppNavHost(
    budgetViewModel: BudgetViewModel,
    transactionViewModel: TransactionViewModel
) {
    var screen by remember { mutableStateOf<Screen>(Screen.Splash) }

    when (val s = screen) {
        is Screen.Splash -> SplashScreen(onNavigateToHome = { screen = Screen.Home })
        is Screen.Home -> HomeNavHost(
            budgetViewModel = budgetViewModel,
            transactionViewModel = transactionViewModel,
            onNavigateToDetails = { data -> screen = Screen.Details(data) },
            onOpenBudget = { screen = Screen.BudgetForm }
        )
        is Screen.Details -> DetailScreen(data = s.data, onBack = { screen = Screen.Home })
        is Screen.BudgetForm -> BudgetFormScreen(
            onSave = { budget ->
                budgetViewModel.addBudget(budget)
                screen = Screen.Home
            },
            onCancel = { screen = Screen.Home }
        )
    }
}
