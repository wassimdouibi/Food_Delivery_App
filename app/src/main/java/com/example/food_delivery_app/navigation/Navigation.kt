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
                navController = navController
            )
        }

        composable(Screen.OTPScreen.route) {
            OTPScreen(
                navController = navController
            )
        }

        composable(Screen.ResetPassword.route) {
            ResetPassword(
                navController = navController
            )
        }

        composable(Screen.EditProfileView.route) {
            val viewModel: EditProfileViewModel = viewModel() // Instantiate or inject ViewModel
            EditProfileView(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable(Screen.ProfileView.route) {
            ProfileView(
                navController = navController
            )
        }

        composable(Screen.NotificationsSettingsView.route) {
            NotificationsSettingsView(
                navController = navController
            )
        }
    }
}