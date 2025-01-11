package com.example.food_delivery_app.core.home.model.entity

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("categoryId")
    val categoryId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String?
)
