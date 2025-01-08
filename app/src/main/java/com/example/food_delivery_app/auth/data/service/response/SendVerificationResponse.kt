package com.example.food_delivery_app.auth.data.service.response

import com.google.gson.annotations.SerializedName

data class SendVerificationResponse(
    val message: String,
    val status: String
)