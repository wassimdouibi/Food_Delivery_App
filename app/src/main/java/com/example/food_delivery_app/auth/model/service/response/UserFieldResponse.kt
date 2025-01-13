package com.example.food_delivery_app.auth.model.service.response

import com.google.gson.annotations.SerializedName

data class UserFieldResponse(
    @SerializedName("userid") val userid: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("phonenumber") val phonenumber: String?,
    @SerializedName("address") val address: String?,
    @SerializedName("password") val password: String?,
    @SerializedName("profilepicture") val profilepicture: String?,
    @SerializedName("googleid") val googleid: String?,
)
