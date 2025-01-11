package com.example.food_delivery_app.core.components

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme


@Composable
fun BorderlessTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,

    textContent: String,
    textStyle: TextStyle,

    icon: ButtonIcon = ButtonIcon.None,
    contentColor: Color = LocalCustomColorScheme.current.primary500,
    containerColor: Color = Color.Transparent,
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .background(containerColor) // Apply background here
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        TextWithIconRow(
            textContent = textContent,
            textStyle = textStyle.copy(
                color = contentColor
            ),
            icon = icon,
            modifier = Modifier.padding(8.dp)
        )
    }
}