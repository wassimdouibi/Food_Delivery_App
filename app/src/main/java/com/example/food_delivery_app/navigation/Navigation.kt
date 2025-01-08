package com.example.food_delivery_app.navigation

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import auth.presentation.forgotpassword.view.ResetPassword
import com.example.food_delivery_app.auth.domain.AuthViewModel
import com.example.food_delivery_app.auth.presentation.forgotpassword.view.ForgotPassword
import com.example.food_delivery_app.auth.presentation.forgotpassword.view.OTPScreen
import com.example.food_delivery_app.auth.presentation.login.view.Login
import com.example.food_delivery_app.auth.presentation.signup.view.Signup
import com.example.food_delivery_app.core.profile.EditProfileView
import com.example.food_delivery_app.core.profile.NotificationsSettingsView
import com.example.food_delivery_app.onboarding.presentation.Onboarding
import com.example.food_delivery_app.splash.presentation.Splash
import com.example.parkir.views.core.profile.ProfileView
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.food_delivery_app.auth.data.entity.AuthPreferences
import com.example.food_delivery_app.core.profile.domain.EditProfileViewModel

@Composable
fun Navigation(
    authViewModel: AuthViewModel,
    pref: SharedPreferences
) {
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState(initial = false)
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) Screen.ProfileView.route else Screen.Onboarding.route
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

        composable(Screen.ForgotPassword.route) {
            ForgotPassword(
                navController = navController,
                authViewModel = authViewModel,
            )
        }

//        composable(
//            route = Screen.OTPScreen.route,
//            arguments = listOf(navArgument("email") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val email = backStackEntry.arguments?.getString("email") ?: ""
//            OTPScreen(
//                navController = navController,
//                authViewModel = authViewModel,
//                email = email
//            )
//        }

        composable(Screen.OTPScreen.route) {
            navBackStackEntry ->
                val email = navBackStackEntry.arguments?.getString("email")
                OTPScreen(
                    navController = navController,
                    authViewModel = authViewModel,
                    email = email!!
                )
        }

        composable(Screen.ResetPassword.route) {
            navBackStackEntry ->
                val email = navBackStackEntry.arguments?.getString("email")

                ResetPassword(
                    navController = navController,
                    authViewModel = authViewModel,
                    email = email!!
                )
        }

        composable(Screen.EditProfileView.route) {
            EditProfileView(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable(Screen.ProfileView.route) {
            ProfileView(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable(Screen.NotificationsSettingsView.route) {
            NotificationsSettingsView(
                navController = navController
            )
        }
    }
}