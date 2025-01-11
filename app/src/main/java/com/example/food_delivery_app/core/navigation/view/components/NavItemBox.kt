package com.example.food_delivery_app.core.navigation.view.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.food_delivery_app.core.navigation.model.NavItem
import com.example.food_delivery_app.ui.theme.Colors.defaultCustomColorScheme

@Composable
fun RowScope.NavItemBox(
    item: NavItem,
    isSelected: Boolean,
    onClick: () -> Unit
){
    NavigationBarItem(
        selected = isSelected,
        onClick = onClick,

        label = { Text( text = item.label) },
        icon = {
            when (item.icon) {
                is CustomIconType.VectorIcon ->
                    Icon(
                        imageVector = item.icon.imageVector,
                        contentDescription = item.icon.iconDescription
                    )
                is CustomIconType.PainterIcon ->
                    Icon(
                        painter = painterResource(item.icon.resourceId),
                        contentDescription = item.icon.iconDescription
                    )
            }
        },
        colors = NavigationBarItemDefaults.colors(
            unselectedTextColor = defaultCustomColorScheme.ink300,
            selectedTextColor = defaultCustomColorScheme.primary500,
            selectedIconColor = defaultCustomColorScheme.primary500,
            unselectedIconColor = defaultCustomColorScheme.ink300,
            indicatorColor = Color.Transparent
        )
    )
}