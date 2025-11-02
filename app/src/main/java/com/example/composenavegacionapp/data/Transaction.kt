package com.example.composenavegacionapp.data

import java.text.SimpleDateFormat
import java.util.UUID
import java.util.Locale

data class Transaction(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val subtitle: String,
    val amount: Double,
    val icon: String,
    val isPositive: Boolean,
    val category: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val description: String = "",
    val paymentMethod: String = "Efectivo"
) {
    fun getFormattedDate(): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.forLanguageTag("es-ES"))
        return sdf.format(createdAt)
    }
    
    fun getFormattedAmount(): String {
        return String.format(Locale.forLanguageTag("es-ES"), "S/ %.2f", amount)
    }
}
