package com.fiap.latteconnect.model

data class CollectionPoint(
    val id: String,
    val name: String,
    val type: String,
    val neighborhood: String,
    val address: String,
    val city: String,
    val cep: String,
    val distanceKm: Double,
    val phone: String,
    val openingHours: String,
    val stockLiters: Int,
    val priority: String,
    val availableSlots: List<String>,
    val notes: String
)
