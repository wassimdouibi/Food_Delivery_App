package com.example.food_delivery_app.core.home.model.services.response

import com.example.food_delivery_app.core.home.model.entity.Category
import com.example.food_delivery_app.core.home.model.entity.Food

data class FoodResponse (
    val food: Food,
    val category: Category,
    val pics: List<String>
)