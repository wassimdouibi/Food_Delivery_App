package com.example.food_delivery_app.core.navigation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.food_delivery_app.core.home.viewModel.HomeViewModel
import com.example.food_delivery_app.core.navigation.model.NavItem
import com.example.food_delivery_app.core.navigation.view.components.NavItemBox
import com.example.food_delivery_app.core.orders.viewModel.OrdersViewModel
import com.example.food_delivery_app.core.favorites.viewModel.FavoritesViewModel
import com.example.food_delivery_app.router.Router
import com.example.food_delivery_app.ui.theme.Colors.defaultCustomColorScheme


@Composable
fun FoodDeliveryNavView(
    navController: NavHostController,
    ordersViewModel: OrdersViewModel,
    favoritesViewModel: FavoritesViewModel,
    homeViewModel: HomeViewModel,
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
                    .fillMaxSize(),
                ) {

                NavHost(navController = navBarController, startDestination = Router.HomeScreen.route)
                {
//                    composable(route = Router.HomeScreen.route) {
//                        HomeView(
//                            navController = navController
//                        )
//                    }
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
//                    composable(route = Router.ProfileScreen.route) {
//                        ProfileView(navController = navController, authViewModel = authViewModel)
//                    }
                }
            }
        }
    )
}