package com.example.food_delivery_app.auth.Model.service.request

data class UpdateProfilePictureRequest(
    val userId: String,
    val newProfilePicture: String
)