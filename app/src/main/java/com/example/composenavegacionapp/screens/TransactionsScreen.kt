package com.example.composenavegacionapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composenavegacionapp.components.TransactionItem

@Composable
fun TransactionsScreen(onNavigateToDetails: (String) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Transacciones", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onBackground)
            Spacer(modifier = Modifier.height(12.dp))

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
