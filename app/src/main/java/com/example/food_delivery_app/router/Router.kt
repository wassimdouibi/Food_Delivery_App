package com.example.food_delivery_app.router

import androidx.navigation.NavType
import com.example.food_delivery_app.core.RestaurantDetailsView
import com.example.food_delivery_app.core.home.model.services.response.RestaurantResponse
import com.google.gson.Gson

sealed class Router(val route:String) {
    object Splash: Router("/splash")
    object OnboardingScreen: Router("/onboarding")

    object LoginScreen: Router("/login")
    object SignupScreen: Router("/signup")
    object ForgotPasswordScreen: Router("/forgot_password")
    object OTPScreen: Router("/otp_screen?email={email}") {
        fun createRoute(email: String): String {
            return "/otp_screen?email=$email"
        }
    }
    object ResetPasswordScreen: Router("/reset_password?email={email}"){
        fun createRoute(email: String): String {
            return "/reset_password?email=$email"
        }
    }

    object FoodDeliveryNavScreen: Router("/nav")

    object HomeScreen: Router("/home")
    object RestaurantDetailsScreen : Router("/restaurant/{restaurantId}") {
        fun createRoute(restaurantId: Int): String {
            return "/restaurant/$restaurantId"
        }
    }

    //    object HomeSearchResultScreen: Router("/search_result/{initialSearchInput}"){
//        fun createRoute(initialSearchInput: String): String = "/search_result/$initialSearchInput"
//    }
//
//    object OrdersScreen: Router("/orders")
//    object FavoritesScreen: Router("/favorites")
//
    object ProfileScreen: Router("/profile")
    object EditProfileScreen: Router("/profile-edit?fromSignup={fromSignup}") {
        fun createRoute(fromSignup: Boolean = false): String {
            return "/profile-edit?fromSignup=$fromSignup"
        }
    }
    object NotificationsSettingsScreen: Router("/notification_settings")
}