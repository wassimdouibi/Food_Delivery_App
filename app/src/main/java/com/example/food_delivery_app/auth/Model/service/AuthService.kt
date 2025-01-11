package com.example.food_delivery_app.auth.Model.service

import com.example.food_delivery_app.NetworkModule
import com.example.food_delivery_app.auth.Model.service.request.UpdateAddressRequest
import com.example.food_delivery_app.auth.Model.service.request.UpdateNameRequest
import com.example.food_delivery_app.auth.Model.service.request.UpdatePhoneNumberRequest
import com.example.food_delivery_app.auth.Model.service.request.UpdateProfilePictureRequest
import com.example.food_delivery_app.auth.Model.service.request.*
import com.example.food_delivery_app.auth.Model.service.response.AuthResponse
import com.example.food_delivery_app.auth.Model.service.response.ResetPasswordResponse
import com.example.food_delivery_app.auth.Model.service.response.SendVerificationResponse
import com.example.food_delivery_app.auth.Model.service.response.UserFieldResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface AuthService {
    @POST("users/register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>

    @POST("users/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @POST("users/google-login")
    suspend fun googleLogin(@Body request: GoogleLoginRequest): Response<AuthResponse>

    @POST("users/send-verification")
    suspend fun sendVerification(@Body request: SendVerificationRequest): Response<SendVerificationResponse>

    @POST("users/verify-code")
    suspend fun verifyCodeOTP(@Body request: VerificationCodeRequest): Response<SendVerificationResponse>

    @POST("users/reset-password")
    suspend fun resetPassword(@Body request: ResetPasswordRequest): Response<ResetPasswordResponse>
}