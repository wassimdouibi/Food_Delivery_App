package com.example.food_delivery_app

import android.app.Application
import com.example.food_delivery_app.auth.data.repository.AuthRepository
import com.example.food_delivery_app.auth.data.service.AuthService

class FoodDeliveryApplication : Application() {
    private val authService by lazy { AuthService.getInstance() }
    val authRepository by lazy { AuthRepository(authService, this) }
}