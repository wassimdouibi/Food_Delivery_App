package com.example.food_delivery_app.auth.Model.service.response

data class VerificationCodeResponse(
    val message: String,
    val status: String,
    val token: String? = null
)
