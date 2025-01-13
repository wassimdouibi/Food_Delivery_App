package com.example.food_delivery_app.core.profile.model.repository

import android.util.Log
import com.example.food_delivery_app.auth.model.service.request.UpdateNameRequest
import com.example.food_delivery_app.auth.model.service.request.UpdatePhoneNumberRequest
import com.example.food_delivery_app.auth.model.service.request.UpdateProfilePictureRequest
import com.example.food_delivery_app.auth.model.service.request.UserFieldsRequest
import com.example.food_delivery_app.auth.model.service.response.UserFieldResponse
import com.example.food_delivery_app.core.profile.model.service.ProfileService

class ProfileRepository(val profileService: ProfileService) {
    suspend fun getUserFields(userId: Int): Result<UserFieldResponse> {
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
    suspend fun updateUserName(userId: Int, newName: String) {
        val request = UpdateNameRequest(userId, newName)
        try {
            val response = profileService.updateName(request)
            if (response.isSuccessful) {
                Log.d("UpdateName", "Name updated successfully.")
            } else {
                // Handle error with status code
                when (response.code()) {
                    400 -> Log.e("UpdateName", "Bad Request: ${response.errorBody()?.string()}")
                    404 -> Log.e("UpdateName", "User not found.")
                    else -> Log.e("UpdateName", "Unknown error: ${response.errorBody()?.string()}")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("UpdateName", "Network error: ${e.localizedMessage}")
        }
    }

    // Update user phone number
    suspend fun updateUserPhoneNumber(userId: Int, phoneNumber: String) {
        val request = UpdatePhoneNumberRequest(userId, phoneNumber)
        try {
            val response = profileService.updatePhoneNumber(request)
            if (response.isSuccessful) {
                Log.d("updateUserPhoneNumber", "Name updated successfully.")
            } else {
                // Handle error with status code
                when (response.code()) {
                    400 -> Log.e("updateUserPhoneNumber", "Bad Request: ${response.errorBody()?.string()}")
                    404 -> Log.e("updateUserPhoneNumber", "User not found.")
                    else -> Log.e("updateUserPhoneNumber", "Unknown error: ${response.errorBody()?.string()}")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("updateUserPhoneNumber", "Network error: ${e.localizedMessage}")
        }
    }

    // Update profile picture
    suspend fun updateProfilePicture(userId: Int, imageUrl: String) {
        val request = UpdateProfilePictureRequest(userId, imageUrl)

        try {
            val response = profileService.updateProfilePicture(request)
            if (response.isSuccessful) {
                Log.d("updateProfilePicture", "Name updated successfully.")
            } else {
                // Handle error with status code
                when (response.code()) {
                    400 -> Log.e("updateProfilePicture", "Bad Request: ${response.errorBody()?.string()}")
                    404 -> Log.e("updateProfilePicture", "User not found.")
                    else -> Log.e("updateProfilePicture", "Unknown error: ${response.errorBody()?.string()}")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("updateProfilePicture", "Network error: ${e.localizedMessage}")
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