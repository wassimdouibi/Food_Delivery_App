package com.example.food_delivery_app.core.home.model.entity

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("reviewId")
    val reviewId: Int,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("restaurantId")
    val restaurantId: Int,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("reviewText")
    val reviewText: String?
)