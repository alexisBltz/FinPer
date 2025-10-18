package com.example.composenavegacionapp.data

import java.util.UUID

data class Transaction(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val subtitle: String,
    val amount: Double,
    val icon: String,
    val isPositive: Boolean,
    val category: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
