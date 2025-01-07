package com.example.food_delivery_app.auth.data.entity

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class AuthPreferences(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
        private val TOKEN_KEY = stringPreferencesKey("token")
    }

    // Retrieve the saved userId as a Flow
    val userIdFlow: Flow<String?> = context.dataStore.data
        .map { preferences -> preferences[USER_ID_KEY] }

    // Retrieve the saved token as a Flow
    val tokenFlow: Flow<String?> = context.dataStore.data
        .map { preferences -> preferences[TOKEN_KEY] }

    // Save user ID and token (overwrite the last one)
    suspend fun saveAuthData(userId: String, token: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
            preferences[TOKEN_KEY] = token
        }
    }

    // Clear stored user ID and token
    suspend fun clearAuthData() {
        context.dataStore.edit { preferences -> preferences.clear() }
    }

    // Retrieve user ID immediately (non-Flow alternative)
    suspend fun getUserId(): String? {
        return context.dataStore.data.map { preferences ->
            preferences[USER_ID_KEY]
        }.firstOrNull()
    }

    // Retrieve token immediately (non-Flow alternative)
    suspend fun getToken(): String? {
        return context.dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }.firstOrNull()
    }
}