package com.example.food_delivery_app.core.navigation.view

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.food_delivery_app.auth.viewModel.AuthViewModel
import com.example.food_delivery_app.core.Home.HomeView
import com.example.food_delivery_app.core.home.viewModel.HomeViewModel
import com.example.food_delivery_app.core.navigation.model.NavItem
import com.example.food_delivery_app.core.navigation.view.components.NavItemBox
import com.example.food_delivery_app.core.orders.viewModel.OrdersViewModel
import com.example.food_delivery_app.core.favorites.viewModel.FavoritesViewModel
import com.example.food_delivery_app.core.profile.view.ProfileView
import com.example.food_delivery_app.core.profile.viewmodel.ProfileViewModel
import com.example.food_delivery_app.router.Router
import com.example.food_delivery_app.ui.theme.Colors.defaultCustomColorScheme


@Composable
fun FoodDeliveryNavView(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    homeViewModel: HomeViewModel,
    profileViewModel: ProfileViewModel,
//    favoritesViewModel: FavoritesViewModel,
//    ordersViewModel: OrdersViewModel,
    pref: SharedPreferences
) {
    val navBarController = rememberNavController()
    var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = defaultCustomColorScheme.utilityWhiteBackground,
            ) {
                NavItem.navItems.forEach { item ->
                    NavItemBox(
                        item = item,
                        isSelected = selectedItem == item.id,
                        onClick = {
                            selectedItem = item.id
                            navBarController.navigate(item.route)
                        },
                    )
                }
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                ) {

                NavHost(navController = navBarController, startDestination = Router.HomeScreen.route)
                {
                    composable(route = Router.HomeScreen.route) {
                        HomeView(
                            navController = navController,
                            homeViewModel = homeViewModel
                        )
                    }
//                    composable(route = Router.OrdersScreen.route) {
//                        OrdersView(
//                            navController = navController,
//                            ordersViewModel = OrdersViewModel
//                        )
//                    }
//                    composable(route = Router.FavoritesScreen.route) {
//                        FavoritesView(
//                            navController = navController,
//                            favoritesViewModel = FavoritesViewModel
//                        )
//                    }
                    composable(route = Router.ProfileScreen.route) {
                        ProfileView(
                            navController = navController,
                            authViewModel = authViewModel,
                            profileViewModel = profileViewModel,
                            pref = pref
                        )
                    }
                }
            }
        }
    )
}