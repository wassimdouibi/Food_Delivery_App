package com.example.food_delivery_app.core.home.model.services.response

import com.example.food_delivery_app.core.home.model.entity.Category
import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("categoryId")
    val categoryId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String?
)
