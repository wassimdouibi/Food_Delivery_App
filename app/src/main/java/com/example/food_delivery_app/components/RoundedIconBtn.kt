package com.example.food_delivery_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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

    contentColorEnabled:Color = LocalCustomColorScheme.current.ink50,
    containerColorEnabled:Color = LocalCustomColorScheme.current.primary400,
    contentColorDisabled:Color = LocalCustomColorScheme.current.ink50,
    containerColorDisabled:Color = LocalCustomColorScheme.current.ink400,

){
    IconButton(
        onClick = onClick,
        modifier = modifier,
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = if (enabled) contentColorEnabled else contentColorDisabled,
            containerColor = if (enabled) containerColorEnabled else containerColorDisabled
        ),
    ){
           Icon(
               imageVector = icon,
               contentDescription = iconDescription,
           )
    }

}

@Composable
fun RoundedIconBtn(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,

    icon: Painter,
    iconDescription: String,

    contentColorEnabled:Color = LocalCustomColorScheme.current.ink50,
    containerColorEnabled:Color = LocalCustomColorScheme.current.primary400,
    contentColorDisabled:Color = LocalCustomColorScheme.current.ink50,
    containerColorDisabled:Color = LocalCustomColorScheme.current.ink400,

    ){
    IconButton(
        onClick = onClick,
        modifier = modifier,
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = if (enabled) contentColorEnabled else contentColorDisabled,
            containerColor = if (enabled) containerColorEnabled else containerColorDisabled
        ),
    ){
        Icon(
            painter = icon,
            contentDescription = iconDescription,
        )
    }

}