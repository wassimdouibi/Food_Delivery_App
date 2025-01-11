package com.example.food_delivery_app.core.home.model.services.request

import com.google.gson.annotations.SerializedName

data class RestaurantRequest(
    @SerializedName("restaurant_id") val restaurantId: Int
)