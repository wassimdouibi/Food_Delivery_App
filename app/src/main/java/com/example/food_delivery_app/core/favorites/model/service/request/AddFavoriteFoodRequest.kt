package com.example.food_delivery_app.core.favorites.model.service.request

import com.google.gson.annotations.SerializedName

data class AddFavoriteFoodRequest(
    @SerializedName("user_id") val userId: Int,
    @SerializedName("menu_id") val menuId: Int
)
