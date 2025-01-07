package com.example.food_delivery_app.auth.domain


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_delivery_app.auth.data.entity.AuthPreferences
import com.example.food_delivery_app.auth.data.service.response.AuthResponse
import com.example.food_delivery_app.auth.data.entity.UserRepository
import com.example.food_delivery_app.auth.data.service.request.RegisterRequest
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


//class AuthViewModel(
//    private val repository: UserRepository = UserRepository(),
//     val authPreferences: AuthPreferences
//) : ViewModel() {
//    private val _authState = MutableStateFlow<AuthState>(AuthState.Initial)
//    val authState: StateFlow<AuthState> = _authState.asStateFlow()
//    fun updateUserId(newId: String) {
//        _userId.value = newId
//    }
//
//    // Expose userId as StateFlow
//    private val _userId = MutableStateFlow<String?>(null)
//    val userId = _userId.asStateFlow()
//
//    init {
//        // Collect userId from preferences when ViewModel is created
//        viewModelScope.launch {
//            authPreferences.saveAuthData(id,)
//        }
//    }
//
//    // Check if user is logged in
//    val isLoggedIn: Flow<Boolean> = authPreferences.token.map { token ->
//        !token.isNullOrEmpty()
//    }
//
//    fun googleLogin(
//        googleId: String,
//        context: Context
//    ) {
//        viewModelScope.launch {
//            _authState.value = AuthState.Loading
//            repository.googleLogin(googleId)
//                .onSuccess { response ->
//                    _authState.value = AuthState.Success(response)
//                    authPreferences.saveAuthData(response.userId, response.token)
//                }
//                .onFailure { error ->
//                    _authState.value = AuthState.Error(error.message ?: "Unknown error")
//                }
//        }
//    }
//
//
//    fun login(email: String, password: String) {
//        viewModelScope.launch {
//            _authState.value = AuthState.Loading
//            try {
//                val result = repository.login(email, password)
//                result.fold(
//                    onSuccess = { response ->
//                        authPreferences.saveAuthData(response.userId, response.token)
//                        _authState.value = AuthState.Success(response)
//                    },
//                    onFailure = { exception ->
//                        _authState.value = AuthState.Error(exception.message ?: "Unknown error")
//                    }
//                )
//            } catch (e: Exception) {
//                _authState.value = AuthState.Error(e.message ?: "Unknown error")
//            }
//        }
//    }
//
//
//    fun register(authViewModel: AuthViewModel, email: String, password: String) {
//        viewModelScope.launch {
//            _authState.value = AuthState.Loading
//            try {
//                val result = repository.register(authViewModel,email, password)
//                Log.d("RegistrationResult", result.toString())
//                result.fold(
//                    onSuccess = { response ->
//                        _authState.value = AuthState.Success(response)
//                        authPreferences.saveAuthData(response.userId, response.token)
//                        Log.d("Registration", "user id from register: ${response.userId}")
//                    },
//                    onFailure = { exception ->
//                        _authState.value = AuthState.Error(exception.message ?: "Unknown error")
//                        Log.d("Registration", "Exception from register : ${exception.message}")
//                    }
//                )
//            } catch (e: Exception) {
//                _authState.value = AuthState.Error(e.message ?: "Unknown error")
//                Log.d("Registration", "Exception : ${e.message}")
//            }
//        }
//    }

//    fun logout() {
//        viewModelScope.launch {
//            authPreferences.clearAuthData()
//            _authState.value = AuthState.Initial
//        }
//    }

//}




class AuthViewModel(
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
}


sealed class AuthState {
    object Initial : AuthState()
    object Loading : AuthState()
    data class Success(val data: AuthResponse) : AuthState()
    data class Error(val message: String) : AuthState()
}