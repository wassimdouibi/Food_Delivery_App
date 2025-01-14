package com.example.food_delivery_app.core.orders.model.entity

import java.math.BigDecimal

data class Order(
    val orderId: Int,
    val userId: Int,
    val deliveryAddress: String,
    val deliveryNotes: String?,
    val totalPrice: BigDecimal,
    val status: String
)