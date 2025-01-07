package com.example.food_delivery_app.core.profile.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_delivery_app.auth.data.entity.UpdateNameRequest
import com.example.food_delivery_app.auth.data.entity.UpdatePhoneNumberRequest
import com.example.food_delivery_app.auth.data.entity.UpdateProfilePictureRequest
import com.example.food_delivery_app.auth.data.entity.UserRepository
import com.example.food_delivery_app.auth.data.service.response.UserFieldResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EditProfileViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    // Define all states
    private val _userFields = MutableStateFlow<UserFieldResponse?>(null)
    val userFields = _userFields.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun fetchUserFields(userId: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true  // Start loading
                Log.d("EditProfileViewModel", "Fetching user fields for userId: $userId")

                userRepository.getUserFields(userId)
                    .onSuccess { response ->
                        Log.d("EditProfileViewModel", "Successfully fetched user fields: $response")
                        _userFields.value = response
                        _error.value = null  // Clear any previous errors
                    }
                    .onFailure { exception ->
                        Log.e("EditProfileViewModel", "Failed to fetch user fields", exception)
                        _error.value = exception.message ?: "Failed to fetch user data"
                    }
            } catch (e: Exception) {
                Log.e("EditProfileViewModel", "Exception in fetchUserFields", e)
                _error.value = e.message ?: "An unexpected error occurred"
            } finally {
                _isLoading.value = false  // Stop loading
            }
        }
    }

    suspend fun updateUserName(userId: String, newName: String) {
        _isLoading.value = true
        try {
            userRepository.updateUserName(UpdateNameRequest(userId, newName))
                .onSuccess { userFieldResponse ->
                    _userFields.value = userFieldResponse.copy(name = newName)
                    _error.value = null
                }
                .onFailure { exception ->
                    _error.value = "Failed to update user name: ${exception.message}"
                }
        } catch (e: Exception) {
            _error.value = e.localizedMessage ?: "An unknown error occurred"
        } finally {
            _isLoading.value = false
        }
    }

    suspend fun updateUserPhoneNumber(userId: String, phonenumber: String) {
        _isLoading.value = true
        try {
            userRepository.updateUserPhoneNumber(UpdatePhoneNumberRequest(userId, phonenumber))
                .onSuccess { userFieldResponse ->
                    _userFields.value = userFieldResponse.copy(phonenumber = phonenumber)
                    _error.value = null
                }
                .onFailure { exception ->
                    _error.value = "Failed to update phone number: ${exception.message}"
                }
        } catch (e: Exception) {
            _error.value = e.localizedMessage ?: "An unknown error occurred"
        } finally {
            _isLoading.value = false
        }
    }


    suspend fun updateProfilePicture(userId: String, profilepicture: String) {
        _isLoading.value = true
        try {
            userRepository.updateProfilePicture(UpdateProfilePictureRequest(userId, profilepicture))
                .onSuccess { userFieldResponse ->
                    _userFields.value = userFieldResponse.copy(profilepicture = profilepicture)  // Update profile picture
                    _error.value = null
                }
                .onFailure { exception ->
                    _error.value = "Failed to update profile picture: ${exception.message}"
                }
        } catch (e: Exception) {
            _error.value = e.localizedMessage ?: "An unknown error occurred"
        } finally {
            _isLoading.value = false
        }
    }

}