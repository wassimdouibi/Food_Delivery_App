package com.example.food_delivery_app.auth.Model.service.request

import com.google.gson.annotations.SerializedName

data class SendVerificationRequest(
    @SerializedName("email") val email: String,
)