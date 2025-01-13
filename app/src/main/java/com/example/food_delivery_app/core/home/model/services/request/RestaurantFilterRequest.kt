package com.example.food_delivery_app.core.home.model.services.request

data class RestaurantFilterRequest(
    val cuisineType: String? = null,
    val minRating: Float = 1.0f,
    val maxRating: Float = 5.0f,
    val searchQuery: String? = null
)
