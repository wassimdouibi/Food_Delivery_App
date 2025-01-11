package com.example.food_delivery_app.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme

@Composable
fun Dot(
    height: Dp,
) {
    Box(
        modifier = Modifier
            .size(height = height, width = 3.dp)
            .clip(RoundedCornerShape(percent = 50))
            .background(LocalCustomColorScheme.current.utilitySuccess),
    )
}