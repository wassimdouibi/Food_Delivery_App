package com.example.food_delivery_app.auth.model.service.response

data class UserResponse(
    val id: String,
    val name: String,
    val email: String,
    val address: String?,
    val profilePicture: String?
)