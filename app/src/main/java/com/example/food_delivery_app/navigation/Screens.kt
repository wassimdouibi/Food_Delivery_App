package com.example.food_delivery_app.navigation

sealed class Screen(val route:String) {
    object Splash:Screen("splash")
    object Onboarding:Screen("onboarding")
    object Login:Screen("login")
    object Signup:Screen("signup")
}