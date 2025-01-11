package com.example.food_delivery_app.core.home.model.services.response

import com.example.food_delivery_app.core.home.model.entity.CuisineType
import com.example.food_delivery_app.core.home.model.entity.Restaurant

data class RestaurantResponse(
    val restaurant: Restaurant,
    val cuisineType: CuisineType,
    val tags: List<String>,
    val pics: List<String>
)
