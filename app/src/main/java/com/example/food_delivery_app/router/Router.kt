package com.example.food_delivery_app.router


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

    object FoodDetailsScreen : Router("/food/{foodId}") {
        fun createRoute(foodId: Int): String {
            return "/food/$foodId"
        }
    }

    object HomeSearchResultScreen: Router("/search_result/{initialSearchInput}"){
        fun createRoute(initialSearchInput: String): String {
            return "/search_result/$initialSearchInput"
        }
    }

    object CuisineRestaurantsScreen : Router("/cuisine_restaurants/{cuisineName}") {
        fun createRoute(cuisineName: String) = "/cuisine_restaurants/$cuisineName"
    }

    object FoodCategoryScreen : Router("/food_category/{foodCategoryName}") {
        fun createRoute(foodCategoryName: String) = "/food_category/$foodCategoryName"
    }

    object FavoritesScreen: Router("/favorites")

    //    object OrdersScreen: Router("/orders")
    object ProfileScreen: Router("/profile")
    object EditProfileScreen: Router("/profile-edit?fromSignup={fromSignup}") {
        fun createRoute(fromSignup: Boolean = false): String {
            return "/profile-edit?fromSignup=$fromSignup"
        }
    }
    object NotificationsSettingsScreen: Router("/notification_settings")
}