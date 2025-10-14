package com.example.composenavegacionapp.data

import java.util.UUID

data class Budget(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val amount: Double,
    val category: String,
    val notes: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

