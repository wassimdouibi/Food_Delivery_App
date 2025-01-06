package com.example.food_delivery_app.auth.domain


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class AuthViewModel : ViewModel() {
    private val _authStatus = MutableStateFlow(false)
    val authStatus = _authStatus.asStateFlow()

    fun signIn() {
        _authStatus.value = true
    }

    fun signOut() {
        _authStatus.value = false
    }
}