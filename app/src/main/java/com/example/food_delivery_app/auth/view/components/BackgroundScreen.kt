package com.example.food_delivery_app.auth.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme

@Composable
fun BackgroundScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(LocalCustomColorScheme.current.primary700)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(LocalCustomColorScheme.current.utilityWhiteBackground)
        )
    }
}