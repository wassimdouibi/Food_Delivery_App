package com.example.food_delivery_app.core.orders.model.entity

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Cart(
    @SerializedName("cartId")
    val cartId: Int,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("totalPrice")
    val totalPrice: BigDecimal
)
