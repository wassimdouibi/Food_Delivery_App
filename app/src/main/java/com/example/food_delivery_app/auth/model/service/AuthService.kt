package com.example.food_delivery_app.auth.model.service

import com.example.food_delivery_app.NetworkModule
import com.example.food_delivery_app.auth.model.service.request.*
import com.example.food_delivery_app.auth.model.service.response.AuthResponse
import com.example.food_delivery_app.auth.model.service.response.ResetPasswordResponse
import com.example.food_delivery_app.auth.model.service.response.SendVerificationResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

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

    companion object {
        private var authInterface: AuthService? = null
        fun getInstance(): AuthService {
            if (authInterface == null) {
                authInterface =
                    Retrofit.Builder().baseUrl(NetworkModule.BASE_URL).addConverterFactory(
                        GsonConverterFactory.create()
                    ).build().create(AuthService::class.java)
            }
            return authInterface!!
        }
    }
}