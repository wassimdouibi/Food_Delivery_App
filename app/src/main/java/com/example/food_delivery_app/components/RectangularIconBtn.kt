package com.example.food_delivery_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RectangularIconBtn(
    onClick: () -> Unit,
    icon: ImageVector,
    iconDescription: String,
    contentColor: Color,
    containerColor: Color,
    size: Dp
){
    Icon(
        imageVector = icon,
        contentDescription = iconDescription,
        tint = contentColor,
        modifier = Modifier
            .clickable { onClick() }
            .size(size)
            .clip(RoundedCornerShape(4.dp))
            .background(containerColor)
    )
}

@Composable
fun RectangularIconBtn(
    onClick: () -> Unit,
    icon: Painter ,
    iconDescription: String,
    contentColor: Color,
    containerColor: Color,
    size: Dp
){
    Icon(
        painter = icon,
        contentDescription = iconDescription,
        tint = contentColor,
        modifier = Modifier
            .clickable { onClick() }
            .size(size)
            .clip(RoundedCornerShape(4.dp))
            .background(containerColor)
    )
}