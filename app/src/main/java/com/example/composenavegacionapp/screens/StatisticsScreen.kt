package com.example.composenavegacionapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.composenavegacionapp.viewmodel.TransactionViewModel
import java.text.NumberFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsScreen(viewModel: TransactionViewModel) {
    val transactions = viewModel.transactions
    val transactionsByCategory = viewModel.getTransactionsByCategory()
    val balance = viewModel.getBalance()
    val totalIncome = viewModel.getTotalIncome()
    val totalExpenses = viewModel.getTotalExpenses()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Estadísticas 📊") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Resumen general
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = "Resumen General",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        StatItem(
                            label = "Total Transacciones",
                            value = "${transactions.size}",
                            icon = "📝"
                        )
                        
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 12.dp),
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f)
                        )
                        
                        StatItem(
                            label = "Balance Actual",
                            value = formatCurrency(balance),
                            icon = "💰",
                            valueColor = if (balance >= 0) Color(0xFF4CAF50) else MaterialTheme.colorScheme.error
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        StatItem(
                            label = "Total Ingresos",
                            value = formatCurrency(totalIncome),
                            icon = "📈",
                            valueColor = Color(0xFF4CAF50)
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        StatItem(
                            label = "Total Gastos",
                            value = formatCurrency(totalExpenses),
                            icon = "📉",
                            valueColor = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
            
            // Distribución por categoría
            item {
                Text(
                    text = "Gastos por Categoría",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            
            items(transactionsByCategory.entries.toList()) { (category, categoryTransactions) ->
                val categoryTotal = categoryTransactions.sumOf { 
                    if (it.isPositive) 0.0 else it.amount 
                }
                
                if (categoryTotal > 0) {
                    CategoryCard(
                        category = category,
                        amount = categoryTotal,
                        transactionCount = categoryTransactions.count { !it.isPositive },
                        percentage = if (totalExpenses > 0) (categoryTotal / totalExpenses * 100) else 0.0
                    )
                }
            }
            
            // Promedio de gastos
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    ),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = "Promedios",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        val expenseTransactions = transactions.filter { !it.isPositive }
                        val avgExpense = if (expenseTransactions.isNotEmpty()) 
                            totalExpenses / expenseTransactions.size else 0.0
                        
                        StatItem(
                            label = "Gasto Promedio",
                            value = formatCurrency(avgExpense),
                            icon = "💳"
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        val maxExpense = expenseTransactions.maxByOrNull { it.amount }
                        if (maxExpense != null) {
                            StatItem(
                                label = "Gasto más Alto",
                                value = "${maxExpense.title} - ${formatCurrency(maxExpense.amount)}",
                                icon = "⚠️"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StatItem(
    label: String,
    value: String,
    icon: String,
    valueColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = icon,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            )
        }
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = valueColor
        )
    }
}

@Composable
private fun CategoryCard(
    category: String,
    amount: Double,
    transactionCount: Int,
    percentage: Double
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = category,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "$transactionCount transacción${if (transactionCount != 1) "es" else ""}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
                
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = formatCurrency(amount),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = String.format("%.1f%%", percentage),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Barra de progreso
            LinearProgressIndicator(
                progress = { (percentage / 100).toFloat() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }
}

private fun formatCurrency(amount: Double): String {
    val format = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-MX"))
    return format.format(amount)
}
