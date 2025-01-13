package com.example.food_delivery_app.core.home.model.entity

import com.google.gson.annotations.SerializedName

data class Restaurant(
    @SerializedName("restaurantId")
    val restaurantId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("cuisineType")
    val cuisineType: Int,
    @SerializedName("averageRating")
    val averageRating: Float,
    @SerializedName("reviewCount")
    val reviewCount: Int,
    @SerializedName("contactPhone")
    val contactPhone: String,
    @SerializedName("contactEmail")
    val contactEmail: String,
    @SerializedName("fbLink")
    val fbLink: String?,
    @SerializedName("instaLink")
    val instaLink: String?
)
