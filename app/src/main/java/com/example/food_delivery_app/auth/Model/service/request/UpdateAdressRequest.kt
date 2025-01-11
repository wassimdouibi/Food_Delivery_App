package com.example.food_delivery_app.auth.Model.service.request

data class UpdateAddressRequest(
    val userId: String,
    val newAddress: String
)