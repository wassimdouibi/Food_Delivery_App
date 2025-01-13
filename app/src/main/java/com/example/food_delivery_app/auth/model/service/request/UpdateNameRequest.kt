package com.example.food_delivery_app.auth.model.service.request

data class UpdateNameRequest(
    val userId: Int,
    val newName: String
)
