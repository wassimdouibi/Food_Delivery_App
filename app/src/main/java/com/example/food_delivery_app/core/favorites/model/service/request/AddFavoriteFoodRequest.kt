package com.example.food_delivery_app.core.favorites.model.service.request

import com.google.gson.annotations.SerializedName

data class AddFavoriteFoodRequest(
    @SerializedName("userId") val userId: Int,
    @SerializedName("menuId") val menuId: Int
)
