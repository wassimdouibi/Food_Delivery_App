package com.example.food_delivery_app.core.restaurants.model.service.response

import com.example.food_delivery_app.core.home.model.entity.Category
import com.example.food_delivery_app.core.restaurants.model.entity.Food

data class FoodResponse (
    val food: Food,
    val category: Category,
    val pics: List<String>
)