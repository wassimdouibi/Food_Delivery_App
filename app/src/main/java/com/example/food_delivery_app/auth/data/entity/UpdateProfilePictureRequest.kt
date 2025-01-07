package com.example.food_delivery_app.auth.data.entity

data class UpdateProfilePictureRequest(
    val userId: String,
    val newProfilePicture: String
)