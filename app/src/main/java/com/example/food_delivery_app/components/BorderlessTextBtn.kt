package com.example.food_delivery_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
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
    contentColor: Color = LocalCustomColorScheme.current.primary700,
    containerColor: Color = Color.Transparent,
) {
    val sizedModifier = modifier.sizeModifier(buttonSize)
    val textStyle: TextStyle = if (isPLargeBold) {
        LocalCustomTypographyScheme.current.p_largeBold
    } else if (buttonSize == ButtonSize.SMALL) {
        LocalCustomTypographyScheme.current.p_smallSemiBold
    } else {
        LocalCustomTypographyScheme.current.p_mediumBold
    }

    Box(
        modifier = modifier
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            .then(sizedModifier),
    ) {
        TextWithIconRow(
            textContent = textContent,
            textStyle = textStyle.copy(
                color = contentColor
            ),
            icon = icon,
            modifier = modifier.background(containerColor)
        )
    }
}






//
//     Button(
//        onClick = onClick,
//        modifier = modifier
//            .then(
//                Modifier.clickable(
//                    onClick = onClick,
//                    indication = null, // Disables the ripple effect
//                    interactionSource = remember { MutableInteractionSource() } // Removes default interaction effects
//                )
//            ),
//        enabled = enabled,
//        colors = ButtonDefaults.buttonColors(
//            contentColor = contentColor,
//            containerColor = containerColor
//        ),
//        shape = CutCornerShape(size = 2.dp),
//    ) {
//        TextWithIconRow(
//            textContent = textContent,
//            textStyle = textStyle,
//            icon = icon
//        )
//    }