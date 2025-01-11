package com.example.food_delivery_app.core.restaurants.model.entity

data class Review(
    val reviewId: Int,
    val userId: Int,
    val restaurantId: Int,
    val rating: Int,
    val title: String?,
    val reviewText: String?
)