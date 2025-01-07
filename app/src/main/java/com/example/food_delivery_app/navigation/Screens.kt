package com.example.food_delivery_app.navigation

sealed class Screen(val route:String) {
    object Splash:Screen("/splash")
    object Onboarding:Screen("/onboarding")
    object Login:Screen("/login")
    object Signup:Screen("/signup")
    object ForgotPassword:Screen("/forgot_password")
    object OTPScreen:Screen("/otp_screen")
    object ResetPassword:Screen("/reset_password")
}