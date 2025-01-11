package com.example.food_delivery_app.auth.ViewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.food_delivery_app.auth.Model.entity.AuthPreferences
import com.example.food_delivery_app.auth.Model.entity.AuthState
import com.example.food_delivery_app.auth.Model.repository.AuthRepository
import com.example.food_delivery_app.auth.Model.service.request.ResetPasswordRequest
import com.example.food_delivery_app.auth.Model.service.request.SendVerificationRequest
import com.example.food_delivery_app.auth.Model.service.request.VerificationCodeRequest
import com.example.food_delivery_app.auth.Model.service.response.ResetPasswordResponse
import com.example.food_delivery_app.auth.Model.service.response.SendVerificationResponse
import com.example.food_delivery_app.utils.Resource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class AuthViewModel (val authRepository: AuthRepository, val authPreferences: AuthPreferences) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Initial)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()
    // Expose userId as StateFlow, initialized from preferences
    private val _userId = MutableStateFlow<String?>("-1")
    val userId: StateFlow<String?> = _userId.asStateFlow()
    // Expose userMail as StateFlow, initialized from preferences
    private val _userMail = MutableStateFlow<String?>(null)
    val userMail: StateFlow<String?> = _userMail.asStateFlow()


    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading
    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error


    init {
        viewModelScope.launch {
            authPreferences.userIdFlow.collect { savedUserId ->
                _userId.value = savedUserId ?: "-1"
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
            authRepository.googleLogin(googleId)
                .onSuccess { response ->
                    authPreferences.saveAuthData(response.userId, response.token)
                    Log.d("Auth", "Saved user ID: ${response.userId}")
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
                val result = authRepository.login(email, password)
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
                val result = authRepository.register(email, password)
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
                when (val result: AuthRepository.VerificationResult = authRepository.sendVerificationEmail(request)) {
                    is AuthRepository.VerificationResult.Success -> {
                        _sendVerificationState.value = Resource.Success(
                            SendVerificationResponse(message = result.message, status = "success")
                        )
                    }

                    is AuthRepository.VerificationResult.Error -> {
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

                when (val result = authRepository.verifyCodeOTP(request)) {
                    is AuthRepository.VerificationResult.Success -> {
                        _verificationState.value = Resource.Success(
                            SendVerificationResponse(
                                message = result.message,
                                status = "success"
                            )
                        )
                    }

                    is AuthRepository.VerificationResult.Error -> {
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

    private val _passwordResetState = MutableStateFlow<Resource<ResetPasswordResponse>>(Resource.Idle())
    val passwordResetState = _passwordResetState.asStateFlow()
    fun updatePassword(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _passwordResetState.value = Resource.Loading()
            try {
                val request = ResetPasswordRequest(
                    email = email,
                    phonenumber = null,  // Adjust if phone number is used
                    newPassword = password
                )
                when (val result = authRepository.resetPassword(request)) {
                    is AuthRepository.VerificationResult.Success -> {
                        _passwordResetState.value = Resource.Success(
                            ResetPasswordResponse(
                                result.message,
                                "success"
                            )
                        )
                    }
                    is AuthRepository.VerificationResult.Error -> {
                        _passwordResetState.value = Resource.Error(
                            result.message
                        )
                    }
                }
            } catch (e: Exception) {
                _passwordResetState.value = Resource.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }


    class Factory(
        private val authRepository: AuthRepository,
        private val authPreferences: AuthPreferences
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AuthViewModel(authRepository, authPreferences) as T
        }
    }
}