package com.example.food_delivery_app.auth.Model.entity

import com.example.food_delivery_app.auth.Model.service.response.AuthResponse

sealed class AuthState {
    object Initial : AuthState()
    object Loading : AuthState()
    data class Success(val data: AuthResponse) : AuthState()
    data class Error(val message: String) : AuthState()
}