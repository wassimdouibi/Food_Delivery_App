package com.example.food_delivery_app.auth.data.entity

import android.util.Log
import com.example.food_delivery_app.auth.data.service.NetworkModule
import com.example.food_delivery_app.auth.data.service.request.GoogleLoginRequest
import com.example.food_delivery_app.auth.data.service.request.LoginRequest
import com.example.food_delivery_app.auth.data.service.request.RegisterRequest
import com.example.food_delivery_app.auth.data.service.request.UserFieldsRequest
import com.example.food_delivery_app.auth.data.service.response.AuthResponse
import com.example.food_delivery_app.auth.data.service.response.UserFieldResponse

class UserRepository {
    private val api = NetworkModule.userApi

    suspend fun register(email: String, password: String): Result<AuthResponse> {
        return try {
            val response = api.register(RegisterRequest(email, password))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                val errorBody = response.errorBody()?.string() ?: "Unknown error"
                Log.e("RegisterError", "Error: $errorBody")
                Result.failure(Exception(errorBody))
            }
        } catch (e: Exception) {
            Log.e("RegisterError", "Exception: ${e.message}")
            Result.failure(e)
        }
    }

    suspend fun login(email: String, password: String): Result<AuthResponse> {
        return try {
            val response = api.login(LoginRequest(email, password))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Login failed"
                Log.e("LoginError", "Error: $errorMessage")
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Log.e("LoginError", "Exception: ${e.message}")
            Result.failure(e)
        }
    }

    suspend fun googleLogin(googleId: String): Result<AuthResponse> {
        return try {
            val response = api.googleLogin(GoogleLoginRequest(googleId))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Google login failed"
                Log.e("GoogleLoginError", "Error: $errorMessage")
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Log.e("GoogleLoginError", "Exception: ${e.message}")
            Result.failure(e)
        }
    }

    suspend fun getUserFields(userId: String): Result<UserFieldResponse> {
        return try {
            val response = api.getUserFields(UserFieldsRequest(userId))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                val errorBody = response.errorBody()?.string() ?: "Failed to get user fields"
                Log.e("UserFieldsError", "Error: $errorBody")
                Result.failure(Exception(errorBody))
            }
        } catch (e: Exception) {
            Log.e("UserFieldsError", "Exception: ${e.message}")
            Result.failure(e)
        }
    }

    // Update user name
    suspend fun updateUserName(request: UpdateNameRequest): Result<UserFieldResponse> {
        return handleApiResponse { api.updateName(request) }
    }

    // Update user phone number
    suspend fun updateUserPhoneNumber(request: UpdatePhoneNumberRequest): Result<UserFieldResponse> {
        return handleApiResponse { api.updatePhoneNumber(request) }
    }

    // Update profile picture
    suspend fun updateProfilePicture(request: UpdateProfilePictureRequest): Result<UserFieldResponse> {
        return handleApiResponse { api.updateProfilePicture(request) }
    }

    // Helper function to handle repetitive API response logic
    private inline fun <T> handleApiResponse(apiCall: () -> retrofit2.Response<T>): Result<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                val errorBody = response.errorBody()?.string() ?: "Unknown error"
                Log.e("ApiError", "Error: $errorBody")
                Result.failure(Exception(errorBody))
            }
        } catch (e: Exception) {
            Log.e("ApiException", "Exception: ${e.message}")
            Result.failure(e)
        }
    }
}






//class UserRepository {
//    private val api = NetworkModule.userApi
//
//    suspend fun register(authViewModel: AuthViewModel, email: String, password: String): Result<AuthResponse> {
//        return try {
//            val response = api.register(RegisterRequest(email, password))
//            if (response.isSuccessful && response.body() != null) {
//                authViewModel.updateUserId(response.body()!!.userId)
//                Result.success(response.body()!!)
//            } else {
//                val errorBody = response.errorBody()?.string() ?: "Uknown error"
//                Log.e("RegisterError", "Error: $errorBody")
//                Result.failure(Exception(errorBody))
//            }
//        } catch (e: Exception) {
//            Log.e("RegisterError", "Exception: ${e.message}")
//            Result.failure(e)
//        }
//    }
//
//    suspend fun login(email: String, password: String): Result<AuthResponse> {
//        return try {
//            val response = api.login(LoginRequest(email, password))
//            if (response.isSuccessful) {
//                Result.success(response.body()!!)
//            } else {
//                Result.failure(Exception("Login failed: ${response.message()}"))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//
//    suspend fun googleLogin(googleId: String): Result<AuthResponse> {
//        return try {
//            val response = api.googleLogin(GoogleLoginRequest(googleId))
//            if (response.isSuccessful) {
//                Result.success(response.body()!!)
//            } else {
//                Result.failure(Exception(response.errorBody()?.string() ?: "Unknown error"))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//
//
//    suspend fun getUserFields(userId: String): Result<UserFieldResponse> {
//        return try {
//            Log.d("UserFields", "${userId}")
//            val response = api.getUserFields(UserFieldsRequest(userId))
//            Log.d("UserFields", "Raw response: ${response.raw()}")
//
//            if (response.isSuccessful) {
//                val body = response.body()
//                Log.d("UserFields", "Response body: $body")
//                Result.success(body!!)
//            } else {
//                val errorBody = response.errorBody()?.string()
//                Log.e("UserFields", "Failed with code: ${response.code()}, error: $errorBody")
//                Result.failure(Exception("Failed to get user fields: ${response.code()}"))
//            }
//        } catch (e: Exception) {
//            Log.e("UserFields", "Exception occurred: ${e.message}", e)
//            Result.failure(e)
//        }
//    }
//
//
//    suspend fun updateUserName(request: UpdateNameRequest): Result<UserFieldResponse> {
//        return try {
//            val response = api.updateName(request)
//            if (response.isSuccessful) {
//                Result.success(response.body()!!)
//            } else {
//                Result.failure(Exception("Failed to update user name: ${response.errorBody()?.string()}"))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//
//    suspend fun updateUserPhoneNumber(request: UpdatePhoneNumberRequest): Result<UserFieldResponse> {
//        return try {
//            val response = api.updatePhoneNumber(request)
//            if (response.isSuccessful) {
//                Result.success(response.body()!!)
//            } else {
//                Result.failure(Exception("Failed to update phone number: ${response.errorBody()?.string()}"))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//
//    suspend fun updateProfilePicture(request: UpdateProfilePictureRequest): Result<UserFieldResponse> {
//        return try {
//            val response = api.updateProfilePicture(request)
//            if (response.isSuccessful) {
//                Result.success(response.body()!!)
//            } else {
//                Result.failure(Exception("Failed to update profile picture: ${response.errorBody()?.string()}"))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//
//
//}