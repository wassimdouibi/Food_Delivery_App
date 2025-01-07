package com.example.food_delivery_app.core.profile.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_delivery_app.auth.data.entity.UserRepository
import com.example.food_delivery_app.auth.data.service.response.UserFieldResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EditProfileViewModel(private val repository: UserRepository) : ViewModel() {
    private val _userFields = MutableStateFlow<UserFieldResponse?>(null)
    val userFields = _userFields.asStateFlow()

    fun fetchUserFields(userId: String) {
        viewModelScope.launch {
            repository.getUserFields(userId).onSuccess { response ->
                _userFields.value = response
            }.onFailure { exception ->
                // Handle error
            }
        }
    }
}
