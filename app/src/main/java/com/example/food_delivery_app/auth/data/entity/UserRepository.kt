package com.example.food_delivery_app.auth.data.entity

import com.example.food_delivery_app.auth.data.NetworkModule
import com.example.food_delivery_app.auth.data.service.request.GoogleLoginRequest
import com.example.food_delivery_app.auth.data.service.request.LoginRequest
import com.example.food_delivery_app.auth.data.service.request.RegisterRequest
import com.example.food_delivery_app.auth.data.service.request.UserFieldsRequest
import com.example.food_delivery_app.auth.data.service.response.AuthResponse
import com.example.food_delivery_app.auth.data.service.response.UserFieldResponse
import retrofit2.Response

class UserRepository {
    private val api = NetworkModule.userApi

    suspend fun register(email: String, password: String): Result<AuthResponse> {
        return try {
            val response = api.register(RegisterRequest(email, password))
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: "Unknown error"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

//    suspend fun login(email: String, password: String): Result<AuthResponse> {
//        return try {
//            val response = api.login(LoginRequest(email, password))
//            if (response.isSuccessful) {
//                Result.success(response.body()!!)
//            } else {
//                Result.failure(Exception(response.errorBody()?.string() ?: "Unknown error"))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }

    suspend fun login(email: String, password: String): Result<AuthResponse> {
        return try {
            val response = api.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Login failed: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun googleLogin(googleId: String): Result<AuthResponse> {
        return try {
            val response = api.googleLogin(GoogleLoginRequest(googleId))
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: "Unknown error"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUserFields(userId: String): Result<UserFieldResponse> {
        return try {
            val response = api.getUserFields(UserFieldsRequest(userId))
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to get user fields"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
