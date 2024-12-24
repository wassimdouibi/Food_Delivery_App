package com.example.food_delivery_app.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme


@Composable
fun OrderActionRow(
    button1Text: String,
    button2Text: String,
    onB1Click: () -> Unit,
    onB2Click: () -> Unit,
    ghostButtonColor: Color = LocalCustomColorScheme.current.primary400,
    filledButtonContentColor: Color = Color.White,
    filledButtonContainerColor: Color = LocalCustomColorScheme.current.primary400,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Bouton 1 - GhostTextButton
        GhostTextButton(
            onClick = onB1Click,
            textContent = button1Text,
            contentColor = ghostButtonColor,
            borderColor = ghostButtonColor
        )
        // Bouton 2 - FilledTextButton
        FilledTextButton(
            onClick = onB2Click,
            containerColor = filledButtonContainerColor,
            textContent = button2Text,
            textStyle = LocalCustomTypographyScheme.current.p_mediumBold,
            contentColor = filledButtonContentColor
        )
    }
}

