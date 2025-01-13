package com.example.food_delivery_app.auth.model.entity

sealed class VerificationResult {
    data class Success(val message: String) : VerificationResult()
    data class Error(val message: String) : VerificationResult()
}