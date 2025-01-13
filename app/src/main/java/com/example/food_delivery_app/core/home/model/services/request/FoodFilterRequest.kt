package com.example.food_delivery_app.core.home.model.services.request

import android.icu.math.BigDecimal

data class FoodFilterRequest(
    val category: String? = null,
    val minPrice: BigDecimal? = null,
    val maxPrice: BigDecimal? = null,
    val searchQuery: String? = null
)