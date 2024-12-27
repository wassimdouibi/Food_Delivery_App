package com.example.food_delivery_app.components

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme


@Composable
fun BorderlessTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonSize: ButtonSize = ButtonSize.MEDIUM,
    enabled: Boolean = true,

    textContent: String,
    isPLargeBold: Boolean = false,

    icon: ButtonIcon = ButtonIcon.None,
    contentColor: Color = LocalCustomColorScheme.current.primary400,
    containerColor: Color = Color.Transparent,
) {
    val textStyle: TextStyle = if (isPLargeBold) {
        LocalCustomTypographyScheme.current.p_largeBold
    } else if (buttonSize == ButtonSize.SMALL) {
        LocalCustomTypographyScheme.current.p_smallSemiBold
    } else {
        LocalCustomTypographyScheme.current.p_mediumBold
    }

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            containerColor = containerColor
        ),
        shape = CutCornerShape(size = 2.dp)
    ) {
        TextWithIconRow(
            textContent = textContent,
            textStyle = textStyle,
            icon = icon
        )
    }
}