package com.example.food_delivery_app.core.navigation.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import com.example.food_delivery_app.R
import com.example.food_delivery_app.core.navigation.view.components.CustomIconType
import com.example.food_delivery_app.router.Router

sealed class NavItem(
    val id: Int,
    val icon: CustomIconType,
    val label: String,
    val route: String
) {
    object Home : NavItem(
        id = 0,
        icon = CustomIconType.VectorIcon(
            imageVector = Icons.Filled.Home,
            iconDescription = "Home"
        ),
        label = "Home",
        route = Router.HomeScreen.route
    )

    object Orders : NavItem(
        id = 1,
        icon = CustomIconType.PainterIcon(
            resourceId = R.drawable.orders,
            iconDescription = "Orders"
        ),
        label = "Orders",
        route = Router.OrdersScreen.route
    )

    object Favorites : NavItem(
        id = 2,
        icon = CustomIconType.VectorIcon(
            imageVector = Icons.Filled.Favorite,
            iconDescription = "Favorites"
        ),
        label = "Favorites",
        route = Router.FavoritesScreen.route
    )

    object Profile : NavItem(
        id = 3,
        icon = CustomIconType.VectorIcon(
            imageVector = Icons.Filled.Person,
            iconDescription = "Profile"
        ),
        label = "Profile",
        route = Router.ProfileScreen.route
    )

    companion object {
        val navItems: List<NavItem> = listOf(
            Home, Orders, Favorites, Profile
        )
    }
}