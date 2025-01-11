package com.example.food_delivery_app.core.home.model.services.response

import com.example.food_delivery_app.core.home.model.entity.CuisineType
import com.example.food_delivery_app.core.home.model.entity.Restaurant

import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    @SerializedName("restaurant")
    val restaurant: Restaurant,
    @SerializedName("cuisine_type")
    val cuisineType: CuisineType,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("pics")
    val pics: List<String>
)
