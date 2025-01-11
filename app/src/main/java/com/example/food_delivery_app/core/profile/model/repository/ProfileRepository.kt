package com.example.food_delivery_app.core.profile.model.repository

import android.util.Log
import com.example.food_delivery_app.auth.Model.service.request.UpdateNameRequest
import com.example.food_delivery_app.auth.Model.service.request.UpdatePhoneNumberRequest
import com.example.food_delivery_app.auth.Model.service.request.UpdateProfilePictureRequest
import com.example.food_delivery_app.auth.Model.service.request.UserFieldsRequest
import com.example.food_delivery_app.auth.Model.service.response.UserFieldResponse
import com.example.food_delivery_app.core.profile.model.service.ProfileService

class ProfileRepository(val profileService: ProfileService) {
    suspend fun getUserFields(userId: String): Result<UserFieldResponse> {
        return try {
            val response = profileService.getUserFields(UserFieldsRequest(userId))
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
        return handleApiResponse { profileService.updateName(request) }
    }

    // Update user phone number
    suspend fun updateUserPhoneNumber(request: UpdatePhoneNumberRequest): Result<UserFieldResponse> {
        return handleApiResponse { profileService.updatePhoneNumber(request) }
    }

    // Update profile picture
    suspend fun updateProfilePicture(request: UpdateProfilePictureRequest): Result<UserFieldResponse> {
        return handleApiResponse { profileService.updateProfilePicture(request) }
    }

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
    
    
}