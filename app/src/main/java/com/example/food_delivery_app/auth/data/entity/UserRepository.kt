package com.example.food_delivery_app.auth.data.entity

import android.util.Log
import com.example.food_delivery_app.auth.data.service.NetworkModule
import com.example.food_delivery_app.auth.data.service.request.*
import com.example.food_delivery_app.auth.data.service.response.AuthResponse
import com.example.food_delivery_app.auth.data.service.response.UserFieldResponse
import com.google.android.gms.common.api.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

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






    sealed class VerificationResult {
        data class Success(val message: String) : VerificationResult()
        data class Error(val message: String) : VerificationResult()
    }

    sealed class VerificationState {
        object Idle : VerificationState()
        object Loading : VerificationState()
        data class Success(val message: String) : VerificationState()
        data class Error(val message: String) : VerificationState()
    }

    private val _verificationState = MutableStateFlow<VerificationState>(VerificationState.Idle)
    val verificationState = _verificationState.asStateFlow()

    suspend fun sendVerificationEmail(request: SendVerificationRequest): VerificationResult {
        return try {
            val response = api.sendVerification(request)
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                when (body.status) {
                    "success" -> VerificationResult.Success(body.message)
                    else -> VerificationResult.Error(body.message)
                }
            } else {
                VerificationResult.Error(
                    response.errorBody()?.string() ?: "Unknown error occurred"
                )
            }
        } catch (e: Exception) {
            Log.e("VerificationError", "Error sending verification email", e)
            VerificationResult.Error(e.message ?: "Network error occurred")
        }
    }

    suspend fun verifyCodeOTP(request: VerificationCodeRequest): VerificationResult {
        return try {
            val response = api.verifyCodeOTP(request) // Ensure this matches your ApiService definition
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                when (body.status) {
                    "success" -> VerificationResult.Success(body.message)
                    else -> VerificationResult.Error(body.message)
                }
            } else {
                val errorBody = response.errorBody()?.string() ?: "Verification failed."
                VerificationResult.Error(errorBody)
            }
        } catch (e: Exception) {
            Log.e("VerifyCodeError", "Error during code verification", e)
            VerificationResult.Error(e.message ?: "Network error occurred")
        }
    }


}