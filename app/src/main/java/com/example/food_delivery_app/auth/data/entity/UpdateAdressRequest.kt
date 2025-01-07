package com.example.food_delivery_app.auth.data.entity

data class UpdateAddressRequest(
    val userId: String,
    val newAddress: String
)