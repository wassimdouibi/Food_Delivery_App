package com.example.food_delivery_app.auth.model.service.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    val email: String,
    val password: String
)