package com.example.food_delivery_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme



@Composable
fun FilledTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,

    textContent: String,
    textStyle: TextStyle,
    icon: ButtonIcon = ButtonIcon.None,

    contentColor: Color = LocalCustomColorScheme.current.ink50,
    containerColor: Color = LocalCustomColorScheme.current.primary400
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .border(2.dp, containerColor)
            .background(containerColor),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = containerColor,
            disabledContentColor = contentColor
        ),
        shape = CutCornerShape(size = 2.dp)

    ) {
        TextWithIconRow(
            textContent = textContent,
            textStyle = textStyle,
            icon = icon,
        )
    }

}