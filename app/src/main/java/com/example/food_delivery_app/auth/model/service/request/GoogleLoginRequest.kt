package com.example.food_delivery_app.auth.model.service.request

import com.google.gson.annotations.SerializedName

data class GoogleLoginRequest(
    val googleId: String
)