package com.example.food_delivery_app.auth.domain

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.food_delivery_app.auth.data.entity.User
import com.example.food_delivery_app.auth.data.repository.AuthRepository
import com.example.food_delivery_app.auth.data.service.request.AuthRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import kotlinx.coroutines.tasks.await
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.example.food_delivery_app.utils.PasswordGenerator
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException


class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    var email: String by mutableStateOf("lw_douibi@esi.dz")
    var password: String by mutableStateOf("12345678")
    var rememberMe: Boolean by mutableStateOf(false)

    var authStatus by mutableStateOf(false)
    var userId by mutableStateOf(-1)
    var user: User? by mutableStateOf<User?>(null)

    fun login() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val body = AuthRequest(email = email, password = password)
                val data = authRepository.login(body)
                if (data.isSuccessful) {
                    if (data.body() != null) {
                        var  token = Firebase.messaging.token.await();
                        Log.i("TOKENNE", token)
                        userId = data.body()?.id?.toInt() ?: -1
                        authRepository.saveUserId(userId)
//                        authRepository.saveUser(data.body()!!)
                        authStatus = true
                    }
                }
            }
        }
    }

    fun register() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val body = AuthRequest(email = email, password = password)
                val data = authRepository.register(body)
                if (data.isSuccessful) {
                    if (data.body() != null) {
                        authStatus = true
                    }
                }
            }
        }
    }

    fun signInWthGoogle(context: Context) {

        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false) // Query all google accounts on the device
            .setServerClientId("98997096205-7fn1pkd5mbomsuephopc7o092vgffuf2.apps.googleusercontent.com")
            .build()

        val request =
            GetCredentialRequest.Builder().addCredentialOption(googleIdOption)
                .build()

        val credentialManager = CredentialManager.create(context)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result =
                    credentialManager.getCredential(context, request)

                when (val credential = result.credential) {
                    is Credential -> {
                        if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                            try {
                                val googleIdTokenCredential =
                                    GoogleIdTokenCredential.createFrom(credential.data)

                                val body = AuthRequest(
                                    email = googleIdTokenCredential.id,
                                    password = PasswordGenerator.generatePassword(50)
                                )
                                val data = authRepository.signInWithGoogle(body)
                                if (data.isSuccessful) {
                                    if (data.body() != null) {
                                        authStatus = true
                                    }
                                }


                                authStatus = true

                            } catch (e: GoogleIdTokenParsingException) {
                                Log.e("MainActivity", "GetCredentialException", e)
                            }
                        }
                    }

                }
            } catch (e: GetCredentialException) {
                Log.e("MainActivity", "GetCredentialException", e)
            }
        }

    }


//    suspend fun getSavedUser(id: Int){
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                user = authRepository.getUser(id)
//            }
//        }
//    }

    class Factory(private val authRepository: AuthRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AuthViewModel(authRepository) as T
        }
    }

}