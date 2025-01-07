//package com.example.food_delivery_app
//
//import android.app.Application
//import com.example.food_delivery_app.auth.data.repository.AuthRepository
//
//class FoodDeliveryApplication : Application() {
//    private val authService by lazy { AuthService.getInstance() }
//    val authRepository by lazy { AuthRepository(authService, this) }
//}