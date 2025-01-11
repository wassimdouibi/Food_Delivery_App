package com.example.food_delivery_app.core.favorites.model.service.request

import com.google.gson.annotations.SerializedName

data class AddFavoriteRestaurantRequest(
    @SerializedName("user_id") val userId: Int,
    @SerializedName("restaurant_id") val restaurantId: Int
)
