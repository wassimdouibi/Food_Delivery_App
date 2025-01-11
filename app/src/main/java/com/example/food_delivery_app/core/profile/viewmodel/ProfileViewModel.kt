package com.example.food_delivery_app.core.profile.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.food_delivery_app.auth.Model.service.request.UpdateNameRequest
import com.example.food_delivery_app.auth.Model.service.request.UpdatePhoneNumberRequest
import com.example.food_delivery_app.auth.Model.service.request.UpdateProfilePictureRequest
import com.example.food_delivery_app.auth.Model.service.response.UserFieldResponse
import com.example.food_delivery_app.core.profile.model.repository.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(val profileRepository: ProfileRepository) : ViewModel() {
    // Define all states
    private val _userFields = MutableStateFlow<UserFieldResponse?>(null)
    val userFields = _userFields.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun getUserFields(userId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = profileRepository.getUserFields(userId)
                    .onSuccess { userFields ->
                        _userFields.value = userFields
                        _error.value = null
                    }
                    .onFailure {exception->
                        _error.value = "Failed to fetch user fields: ${exception.message}"
                    }
            } catch (e: Exception) {
                Log.e("UserFieldsError", "Exception: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateUserName(userId: String, newName: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                profileRepository.updateUserName(UpdateNameRequest(userId, newName))
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
    }

    fun updateUserPhoneNumber(userId: String, phoneNumber: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                profileRepository.updateUserPhoneNumber(UpdatePhoneNumberRequest(userId, phoneNumber))
                    .onSuccess { userFieldResponse ->
                        _userFields.value = userFieldResponse.copy(phonenumber = phoneNumber)
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
    }

    fun updateProfilePicture(userId: String, profilePicture: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                profileRepository.updateProfilePicture(UpdateProfilePictureRequest(userId, profilePicture))
                    .onSuccess { userFieldResponse ->
                        _userFields.value = userFieldResponse.copy(profilepicture = profilePicture)
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

    class Factory(val profileRepository: ProfileRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProfileViewModel(profileRepository) as T
        }
    }

}