package com.example.composenavegacionapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.composenavegacionapp.data.Transaction

class TransactionViewModel : ViewModel() {
    // Lista observable de transacciones (BD temporal en memoria)
    private val _transactions = mutableStateListOf<Transaction>()
    val transactions: List<Transaction> get() = _transactions

    init {
        // Datos mock iniciales
        loadMockData()
    }

    private fun loadMockData() {
        _transactions.addAll(
            listOf(
                Transaction(
                    icon = "💰",
                    title = "Salario",
                    subtitle = "18:27 - Abril 30",
                    amount = 4000.00,
                    isPositive = true,
                    category = "Ingreso"
                ),
                Transaction(
                    icon = "🥬",
                    title = "Verduras",
                    subtitle = "17:00 - Abril 24",
                    amount = 100.00,
                    isPositive = false,
                    category = "Alimentación"
                ),
                Transaction(
                    icon = "🏠",
                    title = "Renta",
                    subtitle = "8:30 - Abril 15",
                    amount = 674.40,
                    isPositive = false,
                    category = "Vivienda"
                ),
                Transaction(
                    icon = "⛽",
                    title = "Gasolina",
                    subtitle = "14:20 - Abril 12",
                    amount = 350.00,
                    isPositive = false,
                    category = "Transporte"
                ),
                Transaction(
                    icon = "🎬",
                    title = "Cine",
                    subtitle = "20:15 - Abril 8",
                    amount = 150.00,
                    isPositive = false,
                    category = "Entretenimiento"
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
}
