package com.example.food_delivery_app.navigation

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.food_delivery_app.auth.domain.AuthViewModel
import com.example.food_delivery_app.auth.presentation.login.view.Login
import com.example.food_delivery_app.auth.presentation.signup.view.Signup
import com.example.food_delivery_app.onboarding.presentation.Onboarding
import com.example.food_delivery_app.splash.presentation.Splash


@Composable
fun Navigation(
    authViewModel: AuthViewModel,
    pref: SharedPreferences
) {
    val navController = rememberNavController()

    val userId = pref.getInt("userId",-1);
    val startScreen = if (userId == -1) Screen.Onboarding.route else Screen.Login.route

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
            Login(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable(Screen.Signup.route) {
            Signup(
                navController = navController,
                authViewModel= authViewModel
            )
        }
    }
}