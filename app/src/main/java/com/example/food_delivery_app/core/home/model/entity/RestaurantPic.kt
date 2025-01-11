package com.example.food_delivery_app.core.home.model.entity

import com.google.gson.annotations.SerializedName

data class RestaurantPic(
    @SerializedName("pic_id")
    val picId: Int,
    @SerializedName("restaurant_id")
    val restaurantId: Int,
    @SerializedName("pic_url")
    val picUrl: String
)
