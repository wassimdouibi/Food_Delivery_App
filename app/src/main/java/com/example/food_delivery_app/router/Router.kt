package com.example.food_delivery_app.router

sealed class Router(val route:String) {
    object Splash: Router("/splash")
    object OnboardingScreen: Router("/onboarding")

    object LoginScreen: Router("/login")
    object SignupScreen: Router("/signup")
    object ForgotPasswordScreen: Router("/forgot_password")
    object OTPScreen: Router("/otp_screen")
    object ResetPasswordScreen: Router("/reset_password")

    object FoodDeliveryNavScreen: Router("/nav")

    object HomeScreen: Router("/home")
//    object HomeSearchResultScreen: Router("/search_result/{initialSearchInput}"){
//        fun createRoute(initialSearchInput: String): String = "/search_result/$initialSearchInput"
//    }
//
//    object OrdersScreen: Router("/orders")
//    object FavoritesScreen: Router("/favorites")
//
    object ProfileScreen: Router("/profile")
    object EditProfileScreen: Router("/profile-edit")
    object NotificationsSettingsScreen: Router("/notification_settings")
}