package com.example.food_delivery_app.favorites.data

data class FoodData(
    val foodId: String,
    val foodName: String,
    val foodImageUrl: String,
    val foodPrice: Float,
    val foodInfo: String,
    val restaurantName: String,
    val restaurantLocation: String,
)
