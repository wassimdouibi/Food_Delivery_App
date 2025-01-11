package com.example.food_delivery_app.core.home.model.entity

import com.google.gson.annotations.SerializedName

data class Tag (
    @SerializedName("tag_id")
    val tagId: Int,
    @SerializedName("restaurant_id")
    val restaurantId: Int,
    @SerializedName("tag_name")
    val tagName: String
)
