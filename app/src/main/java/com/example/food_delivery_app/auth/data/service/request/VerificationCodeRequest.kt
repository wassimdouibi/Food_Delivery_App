package com.example.food_delivery_app.auth.data.service.request

data class VerificationCodeRequest(
    val email: String,
    val code: String,
)