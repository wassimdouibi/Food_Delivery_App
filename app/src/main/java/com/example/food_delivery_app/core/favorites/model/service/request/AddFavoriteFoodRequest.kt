package com.example.food_delivery_app.core.favorites.model.service.request

data class AddFavoriteFoodRequest(
    val userId : Int,
    val menuId : Int
)
