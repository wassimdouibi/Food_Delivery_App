package com.example.food_delivery_app.auth.Model.entity

sealed class VerificationResult {
    data class Success(val message: String) : VerificationResult()
    data class Error(val message: String) : VerificationResult()
}