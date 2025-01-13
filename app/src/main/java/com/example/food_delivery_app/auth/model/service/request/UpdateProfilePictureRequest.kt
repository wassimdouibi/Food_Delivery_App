package com.example.food_delivery_app.auth.model.service.request

data class UpdateProfilePictureRequest(
    val userId: Int,
    val newProfilePicture: String
)