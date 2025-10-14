package com.example.composenavegacionapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.composenavegacionapp.data.Budget

class BudgetViewModel : ViewModel() {
    // Lista observable de presupuestos (BD temporal en memoria)
    private val _budgets = mutableStateListOf<Budget>()
    val budgets: List<Budget> get() = _budgets

    init {
        // Datos mock iniciales
        loadMockData()
    }

    private fun loadMockData() {
        _budgets.addAll(
            listOf(
                Budget(
                    name = "Presupuesto Mensual",
                    amount = 3500.00,
                    category = "Ahorros",
                    notes = "Meta de ahorro para este mes"
                ),
                Budget(
                    name = "Supermercado Semanal",
                    amount = 850.50,
                    category = "Alimentación",
                    notes = "Compras de la semana"
                ),
                Budget(
                    name = "Gasolina",
                    amount = 600.00,
                    category = "Transporte",
                    notes = "Presupuesto quincenal"
                ),
                Budget(
                    name = "Renta",
                    amount = 5000.00,
                    category = "Vivienda",
                    notes = "Pago mensual del apartamento"
                ),
                Budget(
                    name = "Entretenimiento",
                    amount = 1200.00,
                    category = "Otros",
                    notes = "Cine, salidas, etc."
                )
            )
        )
    }

    fun addBudget(budget: Budget) {
        _budgets.add(0, budget) // Agregar al inicio
    }

    fun deleteBudget(budget: Budget) {
        _budgets.remove(budget)
    }

    fun getTotalBudget(): Double {
        return _budgets.sumOf { it.amount }
    }

    fun getBudgetsByCategory(category: String): List<Budget> {
        return _budgets.filter { it.category == category }
    }
}

