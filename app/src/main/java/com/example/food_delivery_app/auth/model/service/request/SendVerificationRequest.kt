package com.example.food_delivery_app.auth.model.service.request

import com.google.gson.annotations.SerializedName

data class SendVerificationRequest(
    val email: String,
)