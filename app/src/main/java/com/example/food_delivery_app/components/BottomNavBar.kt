package com.example.food_delivery_app.components

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.food_delivery_app.ui.theme.CustomColorScheme
import com.example.food_delivery_app.ui.theme.defaultCustomColorScheme

/*import com.example.food_delivery_app.screens.HomeScreen
import com.example.food_delivery_app.screens.OrdersScreen
import com.example.food_delivery_app.screens.FavoritesScreen
import com.example.food_delivery_app.screens.ProfileScreen*/

sealed class BottomNavItem(val route: String, val selectedIcon: ImageVector, val unselectedIcon: ImageVector, val label: String) {
    object Home : BottomNavItem("home", Icons.Filled.Home, Icons.Outlined.Home, "Home")
    object Orders : BottomNavItem("Orders", Icons.Filled.Checklist, Icons.Outlined.Checklist, "Orders")
    object Favorites : BottomNavItem("Favorites", Icons.Filled.Favorite, Icons.Outlined.Favorite, "Favorites")
    object Profile : BottomNavItem("profile", Icons.Filled.Person, Icons.Outlined.Person,  "Profile")
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val screens = listOf(
       BottomNavItem.Home , BottomNavItem.Orders, BottomNavItem.Favorites, BottomNavItem.Profile
    )


    NavigationBar(
        modifier = modifier,
        containerColor = defaultCustomColorScheme.utilityWhiteBackground,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        var selectedItemIndex by rememberSaveable {
            mutableStateOf(0)

        }
        screens.forEachIndexed { index, screen ->
            NavigationBarItem(
                selected = if (selectedItemIndex == index) true else false,
                onClick = {
                    selectedItemIndex = index
                    // navController.navigate(screen.route)
                },
                label = {
                    Text(text = screen.label)
                },
                icon = {
                    Icon(imageVector = if(selectedItemIndex == index) screen.selectedIcon else screen.unselectedIcon , contentDescription = "")
                },
                //selected = currentRoute == screen.route,

                colors = NavigationBarItemDefaults.colors(
                    unselectedTextColor =defaultCustomColorScheme.ink300,
                    selectedTextColor = defaultCustomColorScheme.primary500,
                    selectedIconColor = defaultCustomColorScheme.primary500,
                    unselectedIconColor = defaultCustomColorScheme.ink300,
                    indicatorColor = Color.Transparent
                ),
            )
        }
    }
}