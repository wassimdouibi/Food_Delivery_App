package com.example.food_delivery_app.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme

@Composable
fun FilledTextButtonWithIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonSize: ButtonSize = ButtonSize.SMALL,
    shape : Shape = RectangleShape,
    enabled: Boolean = true,

    textContent: String,
    isPLargeBold: Boolean = false,

    icon: ImageVector? = null, // Optional icon on the left
    iconDescription: String? = null, // Description for the icon

    contentColor: Color = LocalCustomColorScheme.current.ink50,
    containerColor: Color = LocalCustomColorScheme.current.primary500
) {
    val sizedModifier = modifier.sizeModifier(buttonSize)
    val textStyle: TextStyle = if (isPLargeBold) {
        LocalCustomTypographyScheme.current.p_largeBold
    } else if (buttonSize == ButtonSize.SMALL) {
        LocalCustomTypographyScheme.current.p_smallSemiBold
    } else {
        LocalCustomTypographyScheme.current.p_mediumBold
    }

    Button(
        onClick = onClick,
        modifier = sizedModifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            containerColor = containerColor
        ),
        shape = shape,


    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = textContent,
                style = textStyle,
                color = contentColor,
                modifier = Modifier.padding(end = 8.dp)
            )

            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = iconDescription,
                    tint = contentColor
                )
            }

        }
    }
}
