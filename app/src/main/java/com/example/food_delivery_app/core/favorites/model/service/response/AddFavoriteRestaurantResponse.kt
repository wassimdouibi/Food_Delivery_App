package com.example.food_delivery_app.core.favorites.model.service.response

import com.google.gson.annotations.SerializedName

data class AddFavoriteRestaurantResponse(
    @SerializedName("favorite_restaurant_id") val favoriteRestaurantId: Int
)
