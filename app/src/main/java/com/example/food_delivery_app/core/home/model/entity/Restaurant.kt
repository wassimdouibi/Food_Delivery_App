package com.example.food_delivery_app.core.home.model.entity

import com.google.gson.annotations.SerializedName

data class Restaurant(
    @SerializedName("restaurant_id")
    val restaurantId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("cuisine_type")
    val cuisineType: Int,
    @SerializedName("average_rating")
    val averageRating: Float,
    @SerializedName("review_count")
    val reviewCount: Int,
    @SerializedName("contact_phone")
    val contactPhone: String,
    @SerializedName("contact_email")
    val contactEmail: String,
    @SerializedName("fb_link")
    val fbLink: String?,
    @SerializedName("insta_link")
    val instaLink: String?
)
