package com.example.food_delivery_app.core.favorites.model.service.request

import com.google.gson.annotations.SerializedName

data class AddFavoriteRestaurantRequest(
    @SerializedName("userId") val userId: Int,
    @SerializedName("restaurantId") val restaurantId: Int
)
