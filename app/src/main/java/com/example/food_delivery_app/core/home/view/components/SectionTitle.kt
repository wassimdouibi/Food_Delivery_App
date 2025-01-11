package com.example.food_delivery_app.core.home.view.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.ui.theme.Colors.defaultCustomColorScheme
import com.example.food_delivery_app.ui.theme.Typography.defaultCustomTypographyScheme

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = defaultCustomTypographyScheme.heading4,
        color = defaultCustomColorScheme.ink500,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}