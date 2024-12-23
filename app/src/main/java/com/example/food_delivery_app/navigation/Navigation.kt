package com.example.food_delivery_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.food_delivery_app.auth.presentation.login.Login
import com.example.food_delivery_app.auth.presentation.signup.Signup
import com.example.food_delivery_app.onboarding.presentation.Onboarding
import com.example.food_delivery_app.splash.presentation.Splash


@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            Splash(navController = navController)
        }
        composable(Screen.Onboarding.route) {
            Onboarding(navController = navController)
        }
        composable(Screen.Login.route) {
            Login(navController = navController)
        }
        composable(Screen.Signup.route) {
            Signup(navController = navController)
        }
    }
}