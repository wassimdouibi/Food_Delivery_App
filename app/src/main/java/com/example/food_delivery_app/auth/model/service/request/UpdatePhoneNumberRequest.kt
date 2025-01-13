package com.example.food_delivery_app.auth.model.service.request

data class UpdatePhoneNumberRequest(
    val userId: Int,
    val newPhoneNumber: String
)