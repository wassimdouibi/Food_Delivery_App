package com.example.food_delivery_app.auth.model.service.response

data class VerificationCodeResponse(
    val message: String,
    val status: String,
    val token: String? = null
)
