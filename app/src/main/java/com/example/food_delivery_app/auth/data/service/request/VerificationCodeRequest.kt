package com.example.food_delivery_app.auth.data.service.request

data class VerificationCodeRequest(
    val emailOrPhoneNumber: String,
    val code: String
)