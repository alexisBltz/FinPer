package com.example.composenavegacionapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.composenavegacionapp.data.Transaction
import java.util.Calendar

class TransactionViewModel : ViewModel() {
    // Lista observable de transacciones (BD temporal en memoria)
    private val _transactions = mutableStateListOf<Transaction>()
    val transactions: List<Transaction> get() = _transactions

    init {
        // Datos mock iniciales
        loadMockData()
    }

    private fun loadMockData() {
        val calendar = Calendar.getInstance()
        
        _transactions.addAll(
            listOf(
                // Mayo
                Transaction(
                    icon = "💰",
                    title = "Salario Mensual",
                    subtitle = "18:27 - Mayo 30",
                    amount = 4500.00,
                    isPositive = true,
                    category = "Ingreso",
                    description = "Pago de salario del mes de mayo",
                    paymentMethod = "Transferencia bancaria",
                    createdAt = calendar.apply { 
                        set(2024, 4, 30, 18, 27) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "💼",
                    title = "Trabajo Freelance",
                    subtitle = "10:15 - Mayo 28",
                    amount = 800.00,
                    isPositive = true,
                    category = "Ingreso",
                    description = "Desarrollo de sitio web para cliente",
                    paymentMethod = "PayPal",
                    createdAt = calendar.apply { 
                        set(2024, 4, 28, 10, 15) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "🛒",
                    title = "Supermercado",
                    subtitle = "19:30 - Mayo 27",
                    amount = 385.50,
                    isPositive = false,
                    category = "Alimentación",
                    description = "Compras mensuales del hogar",
                    paymentMethod = "Tarjeta de débito",
                    createdAt = calendar.apply { 
                        set(2024, 4, 27, 19, 30) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "🥬",
                    title = "Verduras Frescas",
                    subtitle = "17:00 - Mayo 24",
                    amount = 120.00,
                    isPositive = false,
                    category = "Alimentación",
                    description = "Compra en mercado local",
                    paymentMethod = "Efectivo",
                    createdAt = calendar.apply { 
                        set(2024, 4, 24, 17, 0) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "☕",
                    title = "Café Starbucks",
                    subtitle = "08:45 - Mayo 23",
                    amount = 45.00,
                    isPositive = false,
                    category = "Alimentación",
                    description = "Desayuno",
                    paymentMethod = "Tarjeta de crédito",
                    createdAt = calendar.apply { 
                        set(2024, 4, 23, 8, 45) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "🍕",
                    title = "Pizza Domino's",
                    subtitle = "20:30 - Mayo 20",
                    amount = 95.00,
                    isPositive = false,
                    category = "Alimentación",
                    description = "Cena familiar",
                    paymentMethod = "Efectivo",
                    createdAt = calendar.apply { 
                        set(2024, 4, 20, 20, 30) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "🏠",
                    title = "Renta de Departamento",
                    subtitle = "08:30 - Mayo 15",
                    amount = 1200.00,
                    isPositive = false,
                    category = "Vivienda",
                    description = "Pago mensual de alquiler",
                    paymentMethod = "Transferencia bancaria",
                    createdAt = calendar.apply { 
                        set(2024, 4, 15, 8, 30) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "💡",
                    title = "Luz Eléctrica",
                    subtitle = "11:20 - Mayo 14",
                    amount = 156.30,
                    isPositive = false,
                    category = "Servicios",
                    description = "Recibo del mes de abril",
                    paymentMethod = "Pago online",
                    createdAt = calendar.apply { 
                        set(2024, 4, 14, 11, 20) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "💧",
                    title = "Agua Potable",
                    subtitle = "11:45 - Mayo 14",
                    amount = 78.50,
                    isPositive = false,
                    category = "Servicios",
                    description = "Recibo del mes de abril",
                    paymentMethod = "Pago online",
                    createdAt = calendar.apply { 
                        set(2024, 4, 14, 11, 45) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "📱",
                    title = "Plan Celular",
                    subtitle = "09:15 - Mayo 13",
                    amount = 89.00,
                    isPositive = false,
                    category = "Servicios",
                    description = "Plan mensual ilimitado",
                    paymentMethod = "Cargo automático",
                    createdAt = calendar.apply { 
                        set(2024, 4, 13, 9, 15) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "⛽",
                    title = "Gasolina Premium",
                    subtitle = "14:20 - Mayo 12",
                    amount = 380.00,
                    isPositive = false,
                    category = "Transporte",
                    description = "Tanque lleno",
                    paymentMethod = "Tarjeta de crédito",
                    createdAt = calendar.apply { 
                        set(2024, 4, 12, 14, 20) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "🚕",
                    title = "Uber al Trabajo",
                    subtitle = "07:30 - Mayo 10",
                    amount = 32.50,
                    isPositive = false,
                    category = "Transporte",
                    description = "Viaje matutino",
                    paymentMethod = "Tarjeta vinculada",
                    createdAt = calendar.apply { 
                        set(2024, 4, 10, 7, 30) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "🎬",
                    title = "Cine 4DX",
                    subtitle = "20:15 - Mayo 08",
                    amount = 180.00,
                    isPositive = false,
                    category = "Entretenimiento",
                    description = "Película + palomitas",
                    paymentMethod = "Tarjeta de crédito",
                    createdAt = calendar.apply { 
                        set(2024, 4, 8, 20, 15) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "🎮",
                    title = "PlayStation Plus",
                    subtitle = "16:00 - Mayo 07",
                    amount = 120.00,
                    isPositive = false,
                    category = "Entretenimiento",
                    description = "Suscripción mensual",
                    paymentMethod = "Tarjeta de crédito",
                    createdAt = calendar.apply { 
                        set(2024, 4, 7, 16, 0) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "📺",
                    title = "Netflix Premium",
                    subtitle = "12:00 - Mayo 05",
                    amount = 65.00,
                    isPositive = false,
                    category = "Entretenimiento",
                    description = "Plan familiar mensual",
                    paymentMethod = "Cargo automático",
                    createdAt = calendar.apply { 
                        set(2024, 4, 5, 12, 0) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "🏥",
                    title = "Consulta Médica",
                    subtitle = "15:30 - Mayo 03",
                    amount = 250.00,
                    isPositive = false,
                    category = "Salud",
                    description = "Chequeo general",
                    paymentMethod = "Efectivo",
                    createdAt = calendar.apply { 
                        set(2024, 4, 3, 15, 30) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "💊",
                    title = "Farmacia",
                    subtitle = "16:00 - Mayo 03",
                    amount = 85.00,
                    isPositive = false,
                    category = "Salud",
                    description = "Medicamentos recetados",
                    paymentMethod = "Tarjeta de débito",
                    createdAt = calendar.apply { 
                        set(2024, 4, 3, 16, 0) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "📚",
                    title = "Curso Online Udemy",
                    subtitle = "19:00 - Mayo 02",
                    amount = 199.00,
                    isPositive = false,
                    category = "Educación",
                    description = "Curso de Kotlin avanzado",
                    paymentMethod = "PayPal",
                    createdAt = calendar.apply { 
                        set(2024, 4, 2, 19, 0) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "🎯",
                    title = "Gimnasio Mensual",
                    subtitle = "10:00 - Mayo 01",
                    amount = 150.00,
                    isPositive = false,
                    category = "Salud",
                    description = "Membresía mensual",
                    paymentMethod = "Transferencia bancaria",
                    createdAt = calendar.apply { 
                        set(2024, 4, 1, 10, 0) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "🎁",
                    title = "Regalo Cumpleaños",
                    subtitle = "18:30 - Abril 28",
                    amount = 280.00,
                    isPositive = false,
                    category = "Otros",
                    description = "Regalo para amigo",
                    paymentMethod = "Tarjeta de crédito",
                    createdAt = calendar.apply { 
                        set(2024, 3, 28, 18, 30) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "👕",
                    title = "Ropa Nueva",
                    subtitle = "16:45 - Abril 25",
                    amount = 420.00,
                    isPositive = false,
                    category = "Compras",
                    description = "Camisa y pantalón",
                    paymentMethod = "Tarjeta de crédito",
                    createdAt = calendar.apply { 
                        set(2024, 3, 25, 16, 45) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "✈️",
                    title = "Boleto de Avión",
                    subtitle = "14:00 - Abril 20",
                    amount = 850.00,
                    isPositive = false,
                    category = "Viajes",
                    description = "Vuelo a Cusco",
                    paymentMethod = "Tarjeta de crédito",
                    createdAt = calendar.apply { 
                        set(2024, 3, 20, 14, 0) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "💻",
                    title = "Venta de Laptop",
                    subtitle = "11:00 - Abril 18",
                    amount = 2500.00,
                    isPositive = true,
                    category = "Ingreso",
                    description = "Venta de equipo usado",
                    paymentMethod = "Transferencia bancaria",
                    createdAt = calendar.apply { 
                        set(2024, 3, 18, 11, 0) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "🍔",
                    title = "Almuerzo McDonald's",
                    subtitle = "13:30 - Abril 15",
                    amount = 55.00,
                    isPositive = false,
                    category = "Alimentación",
                    description = "Combo del día",
                    paymentMethod = "Efectivo",
                    createdAt = calendar.apply { 
                        set(2024, 3, 15, 13, 30) 
                    }.timeInMillis
                ),
                Transaction(
                    icon = "🎵",
                    title = "Spotify Premium",
                    subtitle = "09:00 - Abril 10",
                    amount = 39.00,
                    isPositive = false,
                    category = "Entretenimiento",
                    description = "Suscripción mensual",
                    paymentMethod = "Cargo automático",
                    createdAt = calendar.apply { 
                        set(2024, 3, 10, 9, 0) 
                    }.timeInMillis
                )
            )
        )
    }

    fun addTransaction(transaction: Transaction) {
        _transactions.add(0, transaction) // Agregar al inicio
    }

    fun deleteTransaction(transaction: Transaction) {
        _transactions.remove(transaction)
    }

    fun getTotalIncome(): Double {
        return _transactions.filter { it.isPositive }.sumOf { it.amount }
    }

    fun getTotalExpenses(): Double {
        return _transactions.filter { !it.isPositive }.sumOf { it.amount }
    }

    fun getBalance(): Double {
        return getTotalIncome() - getTotalExpenses()
    }
    
    fun getTransactionsByCategory(): Map<String, List<Transaction>> {
        return _transactions.groupBy { it.category }
    }
    
    fun getRecentTransactions(limit: Int = 5): List<Transaction> {
        return _transactions.sortedByDescending { it.createdAt }.take(limit)
    }
}
