package com.example.food_delivery_app.core.home.model.entity

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Food(
    @SerializedName("menuId")
    val menuId: Int,
    @SerializedName("restaurantId")
    val restaurantId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("price")
    val price: BigDecimal,
    @SerializedName("category")
    val category: Int,
    @SerializedName("photo")
    val photo: String?
)
