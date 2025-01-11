package com.example.food_delivery_app.core.favorites.model.service.response

import com.google.gson.annotations.SerializedName

data class AddFavoriteFoodResponse(
    @SerializedName("favorite_menu_id") val favoriteMenuId: Int
)