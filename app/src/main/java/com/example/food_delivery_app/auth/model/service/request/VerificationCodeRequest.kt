package com.example.food_delivery_app.auth.model.service.request

data class VerificationCodeRequest(
    val email: String,
    val code: String,
)