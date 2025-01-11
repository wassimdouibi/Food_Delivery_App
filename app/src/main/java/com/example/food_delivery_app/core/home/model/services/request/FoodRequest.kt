package com.example.food_delivery_app.core.home.model.services.request

import com.google.gson.annotations.SerializedName

data class FoodRequest(
    @SerializedName("food_id") val foodId: Int
)
