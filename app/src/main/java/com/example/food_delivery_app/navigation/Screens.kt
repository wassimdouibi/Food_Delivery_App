package com.example.food_delivery_app.navigation

sealed class Screen(val route:String) {
    object Splash:Screen("/splash")
    object Onboarding:Screen("/onboarding")
    object Login:Screen("/login")
    object Signup:Screen("/signup")
    object ForgotPassword:Screen("/forgot_password")
    object OTPScreen:Screen("/otp_screen")
    object ResetPassword:Screen("/reset_password")
    object EditProfileView:Screen("/edit_profile_view")
    object ProfileView:Screen("/profile_view")
    object NotificationsSettingsView:Screen("/notification_settings_view")
    object Home : Screen("/home")
    object RestaurantDetails : Screen("restaurant_details/{restaurantId}")
}