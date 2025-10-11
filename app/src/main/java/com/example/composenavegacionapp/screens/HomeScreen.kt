package com.example.composenavegacionapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composenavegacionapp.components.TransactionItem

@Composable
fun HomeNavHost(onNavigateToDetails: (String) -> Unit, onOpenBudget: () -> Unit) {
    var selectedIndex by remember { mutableStateOf(0) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(text = "FinPer") },
                actions = {
                    IconButton(onClick = { onOpenBudget() }) {
                        Icon(Icons.Default.Add, contentDescription = "Agregar presupuesto")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0 },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                    label = { Text("Inicio") }
                )
                NavigationBarItem(
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1 },
                    icon = { Text("💳") },
                    label = { Text("Transacciones") }
                )
                NavigationBarItem(
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2 },
                    icon = { Text("💰") },
                    label = { Text("Presupuestos") }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedIndex) {
                0 -> HomeScreen(onNavigateToDetails = onNavigateToDetails)
                1 -> TransactionsScreen(onNavigateToDetails = onNavigateToDetails)
                2 -> BudgetFormScreen(onSave = {
                    // mostrar snackbar local y volver a inicio
                    scope.launch {
                        snackbarHostState.showSnackbar("Presupuesto guardado")
                    }
                    selectedIndex = 0
                }, onCancel = {
                    scope.launch { snackbarHostState.showSnackbar("Acción cancelada") }
                    selectedIndex = 0
                })
            }
        }
    }
}
