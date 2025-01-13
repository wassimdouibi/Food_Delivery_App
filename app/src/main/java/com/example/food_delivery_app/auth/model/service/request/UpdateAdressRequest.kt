package com.example.food_delivery_app.auth.model.service.request

data class UpdateAddressRequest(
    val userId: String,
    val newAddress: String
)