package com.example.food_delivery_app.core.orders.model.entity

data class Order(
    val orderLoc: String,
    val deliveryLoc: String,
    val price: Float
)