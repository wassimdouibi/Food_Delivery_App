package com.example.food_delivery_app.core.favorites.model.service.request

data class AddFavoriteRestaurantRequest(
    val userId : Int,
    val restaurantId : Int
)
