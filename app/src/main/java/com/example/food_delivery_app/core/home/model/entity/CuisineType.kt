package com.example.food_delivery_app.core.home.model.entity

import com.google.gson.annotations.SerializedName

data class CuisineType(
    @SerializedName("cuisineTypeId")
    val cuisineTypeId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String?
)
