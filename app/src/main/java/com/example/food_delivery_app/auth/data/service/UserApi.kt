package com.example.food_delivery_app.auth.data.service

import com.example.food_delivery_app.auth.data.entity.*
import com.example.food_delivery_app.auth.data.service.request.GoogleLoginRequest
import com.example.food_delivery_app.auth.data.service.request.LoginRequest
import com.example.food_delivery_app.auth.data.service.request.RegisterRequest
import com.example.food_delivery_app.auth.data.service.request.UserFieldsRequest
import com.example.food_delivery_app.auth.data.service.response.AuthResponse
import com.example.food_delivery_app.auth.data.service.response.UserFieldResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.Response

interface UserApi {
    @POST("users/register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>

    @POST("users/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @POST("users/google-login")
    suspend fun googleLogin(@Body request: GoogleLoginRequest): Response<AuthResponse>

    @POST("users/get-user-fields")
    suspend fun getUserFields(@Body request: UserFieldsRequest): Response<UserFieldResponse>

    @PUT("users/update-address")
    suspend fun updateAddress(@Body request: UpdateAddressRequest): Response<UserFieldResponse>

    @PUT("users/update-name")
    suspend fun updateName(@Body request: UpdateNameRequest): Response<UserFieldResponse>

    @PUT("users/update-profile-picture")
    suspend fun updateProfilePicture(@Body request: UpdateProfilePictureRequest): Response<UserFieldResponse>

    @PUT("users/update-phone-number")
    suspend fun updatePhoneNumber(@Body request: UpdatePhoneNumberRequest): Response<UserFieldResponse>
}