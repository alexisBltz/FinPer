package com.example.composenavegacionapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
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
import com.example.composenavegacionapp.viewmodel.BudgetViewModel
import com.example.composenavegacionapp.viewmodel.TransactionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavHost(
    budgetViewModel: BudgetViewModel,
    transactionViewModel: TransactionViewModel,
    onNavigateToDetails: (String) -> Unit,
    onLogoClicked: () -> Unit,
    onOpenBudget: () -> Unit,
    onOpenSettings: () -> Unit
) {
    var selectedIndex by remember { mutableStateOf(0) }
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    // Hacemos el título clickable para simular el "logo" y notificar al caller
                    Row {
                        Text(
                            text = "FinPer 💰",
                            modifier = Modifier
                                .padding(4.dp)
                                .clickable {
                                    // Llamamos al callback específico de logo
                                    onLogoClicked()
                                },
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                actions = {
                    // El icono de agregar abre el formulario
                    IconButton(onClick = { onOpenBudget() }) {
                        Icon(Icons.Default.Add, contentDescription = "Agregar presupuesto")
                    }
                    // Icono de ajustes
                    IconButton(onClick = { onOpenSettings() }) {
                        Icon(Icons.Default.Settings, contentDescription = "Ajustes")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ) {
                NavigationBarItem(
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0 },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                    label = { Text("Inicio") }
                )
                NavigationBarItem(
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1 },
                    icon = { Text("💳", style = MaterialTheme.typography.titleLarge) },
                    label = { Text("Transacciones") }
                )
                NavigationBarItem(
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2 },
                    icon = { Text("📊", style = MaterialTheme.typography.titleLarge) },
                    label = { Text("Estadísticas") }
                )
                NavigationBarItem(
                    selected = selectedIndex == 3,
                    onClick = { selectedIndex = 3 },
                    icon = { Text("💰", style = MaterialTheme.typography.titleLarge) },
                    label = { Text("Presupuestos") }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedIndex) {
                0 -> HomeScreen(onNavigateToDetails = onNavigateToDetails)
                1 -> TransactionsScreen(
                    viewModel = transactionViewModel,
                    onNavigateToDetails = onNavigateToDetails
                )
                2 -> StatisticsScreen(viewModel = transactionViewModel)
                3 -> BudgetListScreen(viewModel = budgetViewModel)
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
            ){}

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
                    val greeting = when (java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)) {
                        in 0..11 -> "buenos días ☀️"
                        in 12..18 -> "buenas tardes 🌤️"
                        else -> "buenas noches 🌙"
                    }
                    Text(
                        text = greeting,
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
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Tarjetas de resumen financiero mejoradas
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Card de Balance
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "💰",
                            fontSize = 32.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Balance Total",
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "S/ 7,783.00",
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // Card de Gastos
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    ),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "💸",
                            fontSize = 32.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Gastos del Mes",
                            color = MaterialTheme.colorScheme.onErrorContainer.copy(alpha = 0.8f),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "S/ 1,187.40",
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Título de transacciones recientes
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Transacciones Recientes",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Ver todas →",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de transacciones mejorada
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                TransactionItem(
                    icon = "💰",
                    title = "Salario Mensual",
                    subtitle = "18:27 - Mayo 30",
                    amount = "S/ 4,500.00",
                    isPositive = true,
                    color = Color(0xFF4CAF50),
                    onClick = { onNavigateToDetails("Salario Mensual - S/ 4,500.00") }
                )

                TransactionItem(
                    icon = "🛒",
                    title = "Supermercado",
                    subtitle = "19:30 - Mayo 27",
                    amount = "-S/ 385.50",
                    isPositive = false,
                    color = Color(0xFFE91E63),
                    onClick = { onNavigateToDetails("Supermercado - -S/ 385.50") }
                )

                TransactionItem(
                    icon = "🏠",
                    title = "Renta Departamento",
                    subtitle = "08:30 - Mayo 15",
                    amount = "-S/ 1,200.00",
                    isPositive = false,
                    color = Color(0xFFFF5722),
                    onClick = { onNavigateToDetails("Renta - -S/ 1,200.00") }
                )
                
                TransactionItem(
                    icon = "⛽",
                    title = "Gasolina Premium",
                    subtitle = "14:20 - Mayo 12",
                    amount = "-S/ 380.00",
                    isPositive = false,
                    color = Color(0xFF9C27B0),
                    onClick = { onNavigateToDetails("Gasolina - -S/ 380.00") }
                )
            }
        }
    }
}
