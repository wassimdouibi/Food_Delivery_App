package com.example.food_delivery_app.auth.data.entity

import com.google.gson.annotations.SerializedName

class User (
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("phoneNumber")
    val phoneNumber: String? = null,
)