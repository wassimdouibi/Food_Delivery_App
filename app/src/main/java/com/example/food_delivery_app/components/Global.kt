package com.example.food_delivery_app.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

enum class ButtonSize {
    LARGE,
    MEDIUM,
    SMALL
}

sealed class ButtonIcon {
    data class Left(val icon: ImageVector, val description: String) : ButtonIcon()
    data class Right(val icon: ImageVector, val description: String) : ButtonIcon()
    data object None : ButtonIcon()
}

fun Modifier.sizeModifier(buttonSize: ButtonSize): Modifier {
    return when (buttonSize) {
        ButtonSize.LARGE -> this.fillMaxWidth().padding(vertical = 14.dp)
        ButtonSize.MEDIUM -> this.padding(horizontal = 16.dp, vertical = 12.dp)
        ButtonSize.SMALL -> this.padding(horizontal = 12.dp, vertical = 8.dp)
    }
}

fun Modifier.applySquareButtonSize(buttonSize: ButtonSize): Modifier {
    val size = when (buttonSize) {
        ButtonSize.LARGE -> 48.dp
        ButtonSize.MEDIUM -> 32.dp
        ButtonSize.SMALL -> 24.dp
    }
    return this.size(size).padding(4.dp)
}

@Composable
fun TextWithIconRow(
    textContent: String,
    textStyle: TextStyle,
    icon: ButtonIcon
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (icon is ButtonIcon.Left) {
            Icon(
                imageVector = icon.icon,
                contentDescription = icon.description,
            )
        }
        Text(
            text = textContent,
            style = textStyle
        )
        if (icon is ButtonIcon.Right) {
            Icon(
                imageVector = icon.icon,
                contentDescription = icon.description,
                modifier = Modifier.size(textStyle.fontSize.value.dp)
            )
        }
    }
}


enum class OrderControlState {
    ONGOING,
    HISTORY
}

enum class FavoritesControlState {
    RESTAURANTS,
    FOODS
}
