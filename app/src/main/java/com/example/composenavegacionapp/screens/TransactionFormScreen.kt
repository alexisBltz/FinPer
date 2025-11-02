package com.example.composenavegacionapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.composenavegacionapp.data.Transaction
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionFormScreen(onSave: (Transaction) -> Unit, onCancel: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var paymentMethod by remember { mutableStateOf("Efectivo") }
    var icon by remember { mutableStateOf("💵") }
    var isIncome by remember { mutableStateOf(true) }
    var titleError by remember { mutableStateOf<String?>(null) }
    var amountError by remember { mutableStateOf<String?>(null) }

    val categories = listOf(
        "Ingreso", "Alimentación", "Transporte", "Vivienda", 
        "Entretenimiento", "Salud", "Educación", "Servicios",
        "Compras", "Viajes", "Otros"
    )
    val icons = listOf(
        "💰", "💵", "💼", "🥬", "🏠", "⛽", "🎬", "🏥", 
        "📚", "🛒", "💳", "🎯", "✈️", "🎁", "☕", "🍕"
    )
    val paymentMethods = listOf(
        "Efectivo", "Tarjeta de débito", "Tarjeta de crédito",
        "Transferencia bancaria", "PayPal", "Pago online", 
        "Cargo automático", "Tarjeta vinculada"
    )
    
    var categoryExpanded by remember { mutableStateOf(false) }
    var iconExpanded by remember { mutableStateOf(false) }
    var paymentExpanded by remember { mutableStateOf(false) }

    val isFormValid by remember(title, amount) {
        val okTitle = title.isNotBlank()
        val okAmount = try { amount.replace(',', '.').toDouble(); true } catch (_: Exception) { false }
        mutableStateOf(okTitle && okAmount)
    }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Nueva Transacción",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Selector de tipo: Ingreso / Gasto
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                FilterChip(
                    selected = isIncome,
                    onClick = { isIncome = true },
                    label = { Text("Ingreso") },
                    modifier = Modifier.weight(1f),
                    leadingIcon = { Text("📈") }
                )
                FilterChip(
                    selected = !isIncome,
                    onClick = { isIncome = false },
                    label = { Text("Gasto") },
                    modifier = Modifier.weight(1f),
                    leadingIcon = { Text("📉") }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = title,
                onValueChange = { title = it; if (titleError != null) titleError = null },
                label = { Text("Título") },
                placeholder = { Text("ej: Compra en supermercado") },
                modifier = Modifier.fillMaxWidth(),
                isError = titleError != null,
                singleLine = true
            )
            if (titleError != null) {
                Text(
                    text = titleError!!, 
                    color = MaterialTheme.colorScheme.error, 
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it; if (amountError != null) amountError = null },
                label = { Text("Monto") },
                placeholder = { Text("ej: 1000.50") },
                modifier = Modifier.fillMaxWidth(),
                isError = amountError != null,
                singleLine = true,
                leadingIcon = { Text("S/", style = MaterialTheme.typography.bodyLarge) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
            if (amountError != null) {
                Text(
                    text = amountError!!, 
                    color = MaterialTheme.colorScheme.error, 
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción (opcional)") },
                placeholder = { Text("Detalles adicionales...") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2,
                maxLines = 3
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Dropdown de categorías
            ExposedDropdownMenuBox(
                expanded = categoryExpanded,
                onExpandedChange = { categoryExpanded = !categoryExpanded }
            ) {
                OutlinedTextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Categoría") },
                    placeholder = { Text("Selecciona una categoría") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
                    readOnly = true
                )
                ExposedDropdownMenu(
                    expanded = categoryExpanded,
                    onDismissRequest = { categoryExpanded = false }
                ) {
                    categories.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                category = option
                                categoryExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Dropdown de iconos
                ExposedDropdownMenuBox(
                    expanded = iconExpanded,
                    onExpandedChange = { iconExpanded = !iconExpanded },
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedTextField(
                        value = icon,
                        onValueChange = { icon = it },
                        label = { Text("Icono") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = iconExpanded) },
                        readOnly = true
                    )
                    ExposedDropdownMenu(
                        expanded = iconExpanded,
                        onDismissRequest = { iconExpanded = false }
                    ) {
                        icons.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option, style = MaterialTheme.typography.headlineMedium) },
                                onClick = {
                                    icon = option
                                    iconExpanded = false
                                }
                            )
                        }
                    }
                }

                // Dropdown de método de pago
                ExposedDropdownMenuBox(
                    expanded = paymentExpanded,
                    onExpandedChange = { paymentExpanded = !paymentExpanded },
                    modifier = Modifier.weight(1.5f)
                ) {
                    OutlinedTextField(
                        value = paymentMethod,
                        onValueChange = { paymentMethod = it },
                        label = { Text("Método de pago") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = paymentExpanded) },
                        readOnly = true
                    )
                    ExposedDropdownMenu(
                        expanded = paymentExpanded,
                        onDismissRequest = { paymentExpanded = false }
                    ) {
                        paymentMethods.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    paymentMethod = option
                                    paymentExpanded = false
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        var ok = true
                        if (title.isBlank()) { titleError = "Ingrese un título"; ok = false }
                        val amountDouble = try {
                            amount.replace(',', '.').toDouble()
                        } catch (_: Exception) {
                            amountError = "Monto inválido"
                            ok = false
                            0.0
                        }

                        if (ok) {
                            val currentDate = java.text.SimpleDateFormat(
                                "HH:mm - MMMM dd", 
                                java.util.Locale("es", "ES")
                            ).format(java.util.Date())
                            
                            val newTransaction = Transaction(
                                title = title.trim(),
                                subtitle = currentDate,
                                amount = amountDouble,
                                icon = icon,
                                isPositive = isIncome,
                                category = category.ifBlank { "Otros" },
                                description = description.trim(),
                                paymentMethod = paymentMethod
                            )
                            scope.launch {
                                snackbarHostState.showSnackbar("✅ Transacción guardada exitosamente")
                                delay(400)
                                onSave(newTransaction)
                            }
                        }
                    },
                    modifier = Modifier.weight(1f),
                    enabled = isFormValid,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        "Guardar", 
                        color = MaterialTheme.colorScheme.onPrimary, 
                        style = MaterialTheme.typography.labelLarge
                    )
                }

                OutlinedButton(
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("❌ Acción cancelada")
                            delay(300)
                            onCancel()
                        }
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.onBackground
                    )
                ) {
                    Text("Cancelar", style = MaterialTheme.typography.labelLarge)
                }
            }

            SnackbarHost(hostState = snackbarHostState)
        }
    }
}
