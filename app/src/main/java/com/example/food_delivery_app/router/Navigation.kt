package com.example.food_delivery_app.router

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
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
import com.example.food_delivery_app.core.FoodDetailsView
import com.example.food_delivery_app.core.Home.HomeView
import com.example.food_delivery_app.core.HomeSearchResultView
import com.example.food_delivery_app.core.RestaurantDetailsView
import com.example.food_delivery_app.core.favorites.view.FavoritesView
import com.example.food_delivery_app.core.favorites.viewModel.FavoritesViewModel
import com.example.food_delivery_app.core.home.view.CuisineRestaurantsView
import com.example.food_delivery_app.core.home.view.FoodCategoryView
import com.example.food_delivery_app.core.home.viewModel.HomeViewModel
import com.example.food_delivery_app.core.navigation.view.FoodDeliveryNavView
import com.example.food_delivery_app.core.profile.viewmodel.ProfileViewModel
import com.example.food_delivery_app.onboarding.view.OnboardingView

@Composable
fun NavigationHost(
    navController: NavHostController,
    pref: SharedPreferences,

    authViewModel: AuthViewModel,
    profileViewModel: ProfileViewModel,
    homeViewModel: HomeViewModel,
    favoritesViewModel: FavoritesViewModel
//    ordersViewModel: OrdersViewModel,
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
                favoritesViewModel = favoritesViewModel,
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
                favoritesViewModel = favoritesViewModel,
                restaurantId = restaurantId
            )
        }

        composable(
            route = Router.FoodDetailsScreen.route,
            arguments = listOf(
                navArgument("foodId") {
                    type = NavType.IntType
                    nullable = false
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            val foodId = backStackEntry.arguments?.getInt("foodId") ?: -1
            FoodDetailsView(
                navController = navController,
                homeViewModel = homeViewModel,
                profileViewModel = profileViewModel,
                favoritesViewModel = favoritesViewModel,
                foodID = foodId
            )
        }

        composable(
            route = Router.HomeSearchResultScreen.route,
            arguments = listOf(
                navArgument("initialSearchInput") {
                    type = NavType.StringType
                    nullable = false
                    defaultValue = ""
                }
            )
        ) {
            backStackEntry ->
            val initialSearchInput: String = backStackEntry.arguments?.getString("initialSearchInput") ?: ""
            HomeSearchResultView(
                navController = navController,
                homeViewModel = homeViewModel,
                initialSearchInput = initialSearchInput
            )
        }
        composable(
            route = Router.CuisineRestaurantsScreen.route,
            arguments = listOf(navArgument("cuisineName") { type = NavType.StringType })
        ) { backStackEntry ->
            val cuisineName: String = backStackEntry.arguments?.getString("cuisineName") ?: ""
            CuisineRestaurantsView(
                navController = navController,
                homeViewModel = homeViewModel,
                cuisineName = cuisineName
            )
        }
        composable(
            route = Router.FoodCategoryScreen.route,
            arguments = listOf(navArgument("foodCategoryName") { type = NavType.StringType })
        ) { backStackEntry ->
            val foodCategoryName: String = backStackEntry.arguments?.getString("foodCategoryName") ?: ""
            FoodCategoryView(
                navController = navController,
                homeViewModel = homeViewModel,
                foodCategoryName = foodCategoryName
            )
        }




//        //  ------------------- Orders Screen -------------------
//        composable(Router.OrdersScreen.route) {
//            OrdersView(navController = navController, ordersViewModel = ordersViewModel)
//        }
//
//
        //  ------------------- Favorites Screen -------------------
        composable(Router.FavoritesScreen.route) {
            FavoritesView(
                navController = navController,
                favoritesViewModel = favoritesViewModel
            )
        }
    }
}