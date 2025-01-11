package com.example.food_delivery_app.core.home.model.services.response

import com.example.food_delivery_app.core.home.model.entity.Category
import com.example.food_delivery_app.core.home.model.entity.Food
import com.google.gson.annotations.SerializedName

data class FoodResponse(
    @SerializedName("food")
    val food: Food,
    @SerializedName("category")
    val category: Category,
    @SerializedName("pics")
    val pics: List<String>
)