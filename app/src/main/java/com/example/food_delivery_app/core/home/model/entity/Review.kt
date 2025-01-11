package com.example.food_delivery_app.core.home.model.entity

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("review_id")
    val reviewId: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("restaurant_id")
    val restaurantId: Int,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("review_text")
    val reviewText: String?
)