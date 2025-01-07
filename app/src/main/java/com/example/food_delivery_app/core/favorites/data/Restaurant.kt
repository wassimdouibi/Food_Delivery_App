package com.example.food_delivery_app.favorites.data

data class Restaurant(
    val restaurantId: String,
    val restaurantName: String,
    val restaurantImageUrl: String,
    val restaurantLocation: String,
    val topPicks: List<String>
)
