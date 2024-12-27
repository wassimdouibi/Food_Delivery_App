package com.example.food_delivery_app.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme


@Composable
fun RoundedIconBtn(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,

    icon: ImageVector,
    iconDescription: String,

    contentColor:Color = LocalCustomColorScheme.current.ink50,
    containerColor:Color = LocalCustomColorScheme.current.primary400,
){
    IconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = if (enabled) contentColor else LocalCustomColorScheme.current.ink400,
            containerColor = containerColor
        )
    ){
           Icon(
               imageVector = icon,
               contentDescription = iconDescription,
               modifier = Modifier
                   .padding(4.dp)
           )
    }

}