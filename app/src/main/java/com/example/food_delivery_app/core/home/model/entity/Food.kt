package com.example.food_delivery_app.core.home.model.entity

import java.math.BigDecimal

data class Food(
    val menuId: Int,
    val restaurantId: Int,
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val category: Int,
    val photo: String?
)
