package com.example.composenavegacionapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composenavegacionapp.components.TransactionItem

@OptIn(ExperimentalMaterial3Api::class)
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


@Composable
fun HomeScreen(onNavigateToDetails: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "16:04",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("📶", fontSize = 16.sp)
                    Text("📶", fontSize = 16.sp)
                    Text("🔋", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Saludo
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Hola",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "buenos días",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                    )
                }

                IconButton(
                    onClick = { /* campana para notificaciones - comportamiento futuro */ },
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                            CircleShape
                        )
                ) {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = "Notificaciones",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Tarjetas de presupuesto (resumidas)
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "💰 Presupuesto Total",
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.9f),
                                fontSize = 14.sp
                            )
                            Text(
                                text = "S/7,783.00",
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Column {
                            Text(
                                text = "💸 Gasto Del Mes",
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.9f),
                                fontSize = 14.sp
                            )
                            Text(
                                text = "-S/1,187.40",
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Lista de transacciones
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                TransactionItem(
                    icon = "💰",
                    title = "Salario",
                    subtitle = "18:27 - Abril 30",
                    amount = "S/4,000.00",
                    isPositive = true,
                    color = Color(0xFF64B5F6),
                    onClick = { onNavigateToDetails("Salario - S/4,000.00") }
                )

                TransactionItem(
                    icon = "🥬",
                    title = "Verduras",
                    subtitle = "17:00 - Abril 24",
                    amount = "-S/100.00",
                    isPositive = false,
                    color = Color(0xFF42A5F5),
                    onClick = { onNavigateToDetails("Verduras - -S/100.00") }
                )

                TransactionItem(
                    icon = "🏠",
                    title = "Renta",
                    subtitle = "8:30 - Abril 15",
                    amount = "-S/674.40",
                    isPositive = false,
                    color = Color(0xFF2196F3),
                    onClick = { onNavigateToDetails("Renta - -S/674.40") }
                )
            }
        }
    }
}
