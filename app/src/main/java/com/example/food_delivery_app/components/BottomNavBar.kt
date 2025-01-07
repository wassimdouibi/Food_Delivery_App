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
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.food_delivery_app.R
import com.example.food_delivery_app.ui.theme.CustomColorScheme
import com.example.food_delivery_app.ui.theme.defaultCustomColorScheme

sealed class CustomIconType {
    data class VectorIcon(
        val imageVector: ImageVector,
        val iconDescription: String
    ) : CustomIconType()

    data class PainterIcon(
        val resourceId: Int,
        val iconDescription: String
    ) : CustomIconType()
}


sealed class BottomNavItem(
    val route: String,
    val selectedIcon: CustomIconType,
    val unselectedIcon: CustomIconType,
    val label: String
) {
    object Home : BottomNavItem(
        "home",
        CustomIconType.VectorIcon(
            imageVector = Icons.Filled.Home,
            iconDescription = "Home"
        ),
        CustomIconType.VectorIcon(
            imageVector = Icons.Filled.Home,
            iconDescription = "Home"
        ),
        "Home"
    )

    object Orders : BottomNavItem(
        "orders",
        CustomIconType.PainterIcon(
            resourceId = R.drawable.orders,
            iconDescription = "Orders"
        ),
        CustomIconType.PainterIcon(
            resourceId = R.drawable.orders,
            iconDescription = "Orders"
        ),
        "Orders"
    )

    object Favorites : BottomNavItem(
        "favorites",
        CustomIconType.VectorIcon(
            imageVector = Icons.Filled.Favorite,
            iconDescription = "Favorites"
        ),
        CustomIconType.VectorIcon(
            imageVector = Icons.Filled.Favorite,
            iconDescription = "Favorites"
        ),
        "Favorites"
    )

    object Profile : BottomNavItem(
        "profile_view",
        CustomIconType.VectorIcon(
            imageVector = Icons.Filled.Person,
            iconDescription = "Profile"
        ),
        CustomIconType.VectorIcon(
            imageVector = Icons.Filled.Person,
            iconDescription = "Profile"
        ),
        "Profile"
    )
}

@Composable
fun BottomBar(
    navController: NavController,
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
                    val CustomIconType = if(selectedItemIndex == index) screen.selectedIcon else screen.unselectedIcon
                    when (CustomIconType) {
                        is CustomIconType.VectorIcon -> Icon(imageVector = CustomIconType.imageVector, contentDescription = CustomIconType.iconDescription)
                        is CustomIconType.PainterIcon -> Icon(painter = painterResource(CustomIconType.resourceId), contentDescription = CustomIconType.iconDescription)
                    }
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