package com.example.food_delivery_app.router

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.composable
import com.example.food_delivery_app.auth.viewModel.AuthViewModel
import com.example.food_delivery_app.core.profile.view.EditProfileView
import com.example.food_delivery_app.core.profile.view.NotificationsSettingsView
import com.example.food_delivery_app.splash.Splash
import com.example.food_delivery_app.core.profile.view.ProfileView
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import auth.presentation.forgotpassword.view.ResetPasswordView
import com.example.food_delivery_app.auth.view.forgotpassword.view.ForgotPasswordView
import com.example.food_delivery_app.auth.view.forgotpassword.view.OTPView
import com.example.food_delivery_app.auth.view.login.view.LoginView
import com.example.food_delivery_app.auth.view.signup.view.SignupView
import com.example.food_delivery_app.core.Home.HomeView
import com.example.food_delivery_app.core.RestaurantDetailsView
import com.example.food_delivery_app.core.home.model.services.response.RestaurantResponse
import com.example.food_delivery_app.core.home.viewModel.HomeViewModel
import com.example.food_delivery_app.core.navigation.view.FoodDeliveryNavView
import com.example.food_delivery_app.core.profile.viewmodel.ProfileViewModel
import com.example.food_delivery_app.onboarding.view.OnboardingView
import com.google.gson.Gson

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
//    val isLogin = pref.getBoolean("isLogin", false)
//    Log.d("FoodDeliveryApp", "existing user id is : $isLogin")
//    val startRouter = if (!isLogin) Router.Splash.route else Router.LoginScreen.route

    NavHost(navController = navController, startDestination = Router.LoginScreen.route) {
        composable(Router.Splash.route) {
            Splash(navController = navController)
        }
        composable(Router.OnboardingScreen.route) {
            OnboardingView(navController = navController)
        }


        //  -------------------  Authentication Screens  -------------------
        composable(Router.LoginScreen.route) {
            LoginView(navController = navController, authViewModel = authViewModel, pref = pref)
        }
        composable(Router.SignupScreen.route) {
            SignupView(navController = navController, authViewModel= authViewModel, pref = pref)
        }
        composable(Router.ForgotPasswordScreen.route) {
            ForgotPasswordView(navController = navController, authViewModel = authViewModel, pref= pref)
        }

        composable(
            route = Router.OTPScreen.route,
            arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val email = navBackStackEntry.arguments?.getString("email")!!
            OTPView(navController = navController, authViewModel = authViewModel, email = email)
        }

        composable(
            route = Router.ResetPasswordScreen.route,
            arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val email = navBackStackEntry.arguments?.getString("email")!!
            ResetPasswordView(navController = navController, authViewModel = authViewModel, email = email)
        }


        // -------------------  Profile Screens  -------------------
        composable(Router.ProfileScreen.route) {
            ProfileView(navController = navController, authViewModel = authViewModel, profileViewModel = profileViewModel, pref = pref)
        }

        composable(
            route = Router.EditProfileScreen.route,
            arguments = listOf(
                navArgument("fromSignup") {
                    type = NavType.BoolType
                    defaultValue = false
                }
            )
        ) { navBackStackEntry ->
            val isFromSignup = navBackStackEntry.arguments?.getBoolean("fromSignup") ?: false
            EditProfileView(navController = navController, authViewModel = authViewModel, profileViewModel = profileViewModel, isFromSignup = isFromSignup)
        }

        composable(Router.NotificationsSettingsScreen.route) {
            NotificationsSettingsView(navController = navController)
        }

        //  -------------------  Home  -------------------
        composable(Router.FoodDeliveryNavScreen.route) {
            FoodDeliveryNavView(
                navController = navController,
                authViewModel = authViewModel,
                homeViewModel = homeViewModel,
                profileViewModel = profileViewModel,
                pref = pref
            )
        }

        composable(Router.HomeScreen.route) {
            HomeView(navController = navController, homeViewModel = homeViewModel)
        }

        composable(
            route = Router.RestaurantDetailsScreen.route,
            arguments = listOf(
                navArgument("restaurantId") {
                    type = NavType.IntType
                    nullable = false
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            val restaurantId = backStackEntry.arguments?.getInt("restaurantId") ?: -1
            RestaurantDetailsView(
                navController = navController,
                homeViewModel = homeViewModel,
                profileViewModel = profileViewModel,
                restaurantId = restaurantId
            )
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