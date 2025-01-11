package com.example.food_delivery_app.core.home.model.entity

data class Restaurant(
    val restaurantId: Int,
    val name: String,
    val logo: String,
    val address: String,
    val cuisineType: Int,
    val averageRating: Float,
    val reviewCount: Int,
    val contactPhone: String,
    val contactEmail: String,
    val fbLink: String?,
    val instaLink: String?,
)
