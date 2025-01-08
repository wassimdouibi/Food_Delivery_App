package com.example.food_delivery_app.auth.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_delivery_app.auth.data.entity.AuthPreferences
import com.example.food_delivery_app.auth.data.service.response.AuthResponse
import com.example.food_delivery_app.auth.data.entity.UserRepository
import com.example.food_delivery_app.auth.data.service.request.SendVerificationRequest
import com.example.food_delivery_app.auth.data.service.request.VerificationCodeRequest
import com.example.food_delivery_app.auth.data.service.response.SendVerificationResponse
import com.example.food_delivery_app.utils.Resource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch



class AuthViewModel (
    private val repository: UserRepository = UserRepository(),
    private val authPreferences: AuthPreferences
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Initial)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    // Expose userId as StateFlow, initialized from preferences
    private val _userId = MutableStateFlow<String?>(null)
    val userId: StateFlow<String?> = _userId.asStateFlow()

    init {
        // Load the user ID from preferences when the ViewModel is initialized
        viewModelScope.launch {
            authPreferences.userIdFlow.collect { savedUserId ->
                _userId.value = savedUserId
            }
        }
    }

    // Check if the user is logged in by monitoring the token
    val isLoggedIn: Flow<Boolean> = authPreferences.tokenFlow.map { token ->
        !token.isNullOrEmpty()
    }

    fun googleLogin(googleId: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            repository.googleLogin(googleId)
                .onSuccess { response ->
                    authPreferences.saveAuthData(response.userId, response.token)
                    _userId.value = response.userId
                    _authState.value = AuthState.Success(response)
                }
                .onFailure { error ->
                    _authState.value = AuthState.Error(error.message ?: "Unknown error")
                }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val result = repository.login(email, password)
                result.fold(
                    onSuccess = { response ->
                        authPreferences.saveAuthData(response.userId, response.token)
                        _userId.value = response.userId
                        _authState.value = AuthState.Success(response)
                    },
                    onFailure = { exception ->
                        _authState.value = AuthState.Error(exception.message ?: "Unknown error")
                    }
                )
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val result = repository.register(email, password)
                result.fold(
                    onSuccess = { response ->
                        authPreferences.saveAuthData(response.userId, response.token)
                        _userId.value = response.userId
                        _authState.value = AuthState.Success(response)
                        Log.d("Registration", "User ID from register: ${response.userId}")
                    },
                    onFailure = { exception ->
                        _authState.value = AuthState.Error(exception.message ?: "Unknown error")
                        Log.d("Registration", "Exception from register: ${exception.message}")
                    }
                )
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Unknown error")
                Log.d("Registration", "Exception: ${e.message}")
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authPreferences.clearAuthData()
            _userId.value = null
            _authState.value = AuthState.Initial
        }
    }

    private val _sendVerificationState = MutableStateFlow<Resource<SendVerificationResponse>>(Resource.Idle())
    val sendVerificationState = _sendVerificationState.asStateFlow()

    fun sendVerification(email: String) {
        viewModelScope.launch {
            _sendVerificationState.value = Resource.Loading()
            try {
                Log.d("SendVerification", "verification code sent to : ${email}}")

                val request = SendVerificationRequest(email = email)
                when (val result: UserRepository.VerificationResult = repository.sendVerificationEmail(request)) {
                    is UserRepository.VerificationResult.Success -> {
                        _sendVerificationState.value = Resource.Success(
                            SendVerificationResponse(message = result.message, status = "success")
                        )
                    }

                    is UserRepository.VerificationResult.Error -> {
                        _sendVerificationState.value = Resource.Error(
                            message = result.message,
                            data = null
                        )
                    }
                }

            } catch (e: Exception) {
                _sendVerificationState.value = Resource.Error(
                    message = e.message ?: "An unexpected error occurred",
                    data = null
                )
            }
        }
    }


    private val _verificationState = MutableStateFlow<Resource<SendVerificationResponse>>(Resource.Idle())
    val verificationState = _verificationState.asStateFlow()

    fun verifyCode(emailOrPhoneNumber: String, code: String) {
        viewModelScope.launch {
            Log.d("VerifyCode", "Attempting verification with email: $emailOrPhoneNumber and code: $code")
            _verificationState.value = Resource.Loading()
            try {
                val request = VerificationCodeRequest(
                    email = emailOrPhoneNumber,
                    code = code
                )

                when (val result = repository.verifyCodeOTP(request)) {
                    is UserRepository.VerificationResult.Success -> {
                        _verificationState.value = Resource.Success(
                            SendVerificationResponse(
                                message = result.message,
                                status = "success"
                            )
                        )
                    }

                    is UserRepository.VerificationResult.Error -> {
                        _verificationState.value = Resource.Error(
                            message = result.message,
                            data = null
                        )
                    }
                }
            } catch (e: Exception) {
                _verificationState.value = Resource.Error(
                    message = e.message ?: "An unexpected error occurred",
                    data = null
                )
            }
        }

    }
}


sealed class AuthState {
    object Initial : AuthState()
    object Loading : AuthState()
    data class Success(val data: AuthResponse) : AuthState()
    data class Error(val message: String) : AuthState()
}

sealed class VerificationResult {
    data class Success(val message: String) : VerificationResult()
    data class Error(val message: String) : VerificationResult()
}
