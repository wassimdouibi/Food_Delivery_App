package com.example.food_delivery_app.auth.data.service.request

data class ResetPasswordRequest(
    val email: String? = null,
    val phonenumber: String? = null,
    val newPassword: String
)
