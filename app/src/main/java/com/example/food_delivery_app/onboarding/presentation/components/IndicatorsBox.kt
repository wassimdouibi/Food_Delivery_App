package com.example.food_delivery_app.onboarding.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme


@Composable
fun Indicator(isSelected: Boolean) {

    var width = animateDpAsState(targetValue = if (isSelected) 35.dp else 10.dp, label = "")

    Box(
        modifier = Modifier
            .size(height = 10.dp, width = width.value)
            .clip(RoundedCornerShape(percent = 50))
            .background(
                if (isSelected)
                    LocalCustomColorScheme.current.primary700
                else
                    LocalCustomColorScheme.current.primary200
            ),
    )
}

@Composable
fun IndicatorsBox(nbIndicators: Int, selectedIndicator: Int) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            repeat(nbIndicators) {
                Indicator(isSelected = it == selectedIndicator)
            }
        }
    }
}