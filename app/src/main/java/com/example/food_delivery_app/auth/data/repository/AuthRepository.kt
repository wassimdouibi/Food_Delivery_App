package com.example.food_delivery_app.auth.data.repository

import android.content.Context
import androidx.core.content.edit
import com.example.food_delivery_app.auth.data.entity.User
import com.example.food_delivery_app.auth.data.service.AuthService
import com.example.food_delivery_app.auth.data.service.request.AuthRequest
import retrofit2.Response

class AuthRepository(
    private val authService: AuthService,
    private val context: Context,
) {
    suspend fun login(user: AuthRequest): Response<User> {
        return authService.login(user);
    }

    fun saveUserId(userId: Int){
        val pref =
            context.getSharedPreferences("local"
                ,Context.MODE_PRIVATE
            )

        pref.edit {
            putInt("userId",
                userId)
        }
    }
    suspend fun getUserId(): Int {
        val pref = context.getSharedPreferences(
            "local", Context.MODE_PRIVATE
        )
        return pref.getInt("userId", -1)
    }

    suspend fun register(user: AuthRequest): Response<User> {
        return authService.register(user)
    }

    suspend fun signInWithGoogle(user: AuthRequest): Response<User> {
        return authService.signInWithGoogle(user)
    }
}