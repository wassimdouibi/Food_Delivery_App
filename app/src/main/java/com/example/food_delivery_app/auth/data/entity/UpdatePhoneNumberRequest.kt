package com.example.food_delivery_app.auth.data.entity

data class UpdatePhoneNumberRequest(
    val userId: String,
    val newPhoneNumber: String
)