package com.example.food_delivery_app.router

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.composable
import com.example.food_delivery_app.auth.ViewModel.AuthViewModel
import com.example.food_delivery_app.core.profile.view.EditProfileView
import com.example.food_delivery_app.core.profile.view.NotificationsSettingsView
import com.example.food_delivery_app.splash.Splash
import com.example.food_delivery_app.core.profile.view.ProfileView
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import auth.presentation.forgotpassword.view.ResetPasswordView
import com.example.food_delivery_app.auth.View.forgotpassword.view.ForgotPasswordView
import com.example.food_delivery_app.auth.View.forgotpassword.view.OTPView
import com.example.food_delivery_app.auth.View.login.view.LoginView
import com.example.food_delivery_app.auth.View.signup.view.SignupView
import com.example.food_delivery_app.core.Home.HomeView
import com.example.food_delivery_app.core.home.viewModel.HomeViewModel
import com.example.food_delivery_app.core.orders.viewModel.OrdersViewModel
import com.example.food_delivery_app.core.profile.viewmodel.ProfileViewModel
import com.example.food_delivery_app.core.favorites.viewModel.FavoritesViewModel
import com.example.food_delivery_app.onboarding.View.OnboardingView

@Composable
fun NavigationHost(
    navController: NavHostController,
    pref: SharedPreferences,

    authViewModel: AuthViewModel,
    profileViewModel: ProfileViewModel,
    homeViewModel: HomeViewModel,
//    ordersViewModel: OrdersViewModel,
//    favoritesViewModel: FavoritesViewModel
) {
    val userId by authViewModel.userId.collectAsState()
    val startRouter = if (userId == "-1") Router.Splash.route else Router.HomeScreen.route

    NavHost(navController = navController, startDestination = startRouter) {
        composable(Router.Splash.route) {
            Splash(navController = navController)
        }
        composable(Router.OnboardingScreen.route) {
            OnboardingView(navController = navController)
        }


        //  -------------------  Authentication Screens  -------------------
        composable(Router.LoginScreen.route) {
            LoginView(navController = navController, authViewModel = authViewModel)
        }
        composable(Router.SignupScreen.route) {
            SignupView(navController = navController, authViewModel= authViewModel)
        }
        composable(Router.ForgotPasswordScreen.route) {
            ForgotPasswordView(navController = navController, authViewModel = authViewModel, pref= pref)
        }
        composable(Router.OTPScreen.route) {
            navBackStackEntry ->
                OTPView(navController = navController, authViewModel = authViewModel, pref= pref)
        }
        composable(Router.ResetPasswordScreen.route) {
            navBackStackEntry ->
                ResetPasswordView(navController = navController, authViewModel = authViewModel, pref= pref)
        }


        // -------------------  Profile Screens  -------------------
        composable(Router.ProfileScreen.route) {
            ProfileView(navController = navController, authViewModel = authViewModel, profileViewModel = profileViewModel)
        }
        composable(Router.EditProfileScreen.route) {
            EditProfileView(navController = navController, authViewModel = authViewModel, profileViewModel = profileViewModel)
        }
        composable(Router.NotificationsSettingsScreen.route) {
            NotificationsSettingsView(navController = navController)
        }


//        //  -------------------  Home  -------------------
        composable(Router.HomeScreen.route) {
            HomeView(navController = navController, homeViewModel = homeViewModel)
        }
//        composable(Router.HomeSearchResultScreen.route) {
//            backStackEntry ->
//            val initialSearchInput: String = backStackEntry.arguments?.getString("initialSearchInput")!!
//            HomeSearchResultView(navController = navController, initialSearchInput = initialSearchInput)
//        }
//
//
//        //  ------------------- Main NavScreen -------------------
//        composable(Router.FoodDeliveryNavScreen.route) {
//            FoodDeliveryNavView(
//                navController = navController,
//                ordersViewModel = ordersViewModel,
//                favoritesViewModel = favoritesViewModel
//            )
//        }
//
//
//        //  ------------------- Orders Screen -------------------
//        composable(Router.OrdersScreen.route) {
//            OrdersView(navController = navController, ordersViewModel = ordersViewModel)
//        }
//
//
//        //  ------------------- Favorites Screen -------------------
//        composable(Router.FavoritesScreen.route) {
//            FavoritesView(navController = navController, favoritesViewModel = favoritesViewModel)
//        }
    }
}