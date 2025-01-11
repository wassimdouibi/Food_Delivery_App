package com.example.food_delivery_app.auth.Model.service.request

data class UpdatePhoneNumberRequest(
    val userId: String,
    val newPhoneNumber: String
)