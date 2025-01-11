package com.example.food_delivery_app.core.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.R


sealed class IconType {
    data class VectorIcon(val imageVector: ImageVector, val iconDescription: String) : IconType()
    data class PainterIcon(val painter: Painter, val iconDescription: String) : IconType()
}

sealed class ButtonIcon {
    data class Left(val icon: IconType) : ButtonIcon()
    data class Right(val icon: IconType) : ButtonIcon()
    data object None : ButtonIcon()
}


@Composable
fun TextWithIconRow(
    textContent: String,
    textStyle: TextStyle,
    icon: ButtonIcon,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        if (icon is ButtonIcon.Left) {
            when(icon.icon) {
                is IconType.VectorIcon -> {
                    Icon(
                        imageVector = icon.icon.imageVector,
                        contentDescription = icon.icon.iconDescription,
                        modifier = Modifier.size(textStyle.fontSize.value.dp)
                    )
                }
                is IconType.PainterIcon -> {
                    Icon(
                        painter = icon.icon.painter,
                        contentDescription = icon.icon.iconDescription,
                        modifier = Modifier.size(textStyle.fontSize.value.dp)
                    )
                }
            }
        }

        Text(
            text = textContent,
            style = textStyle
        )

        if (icon is ButtonIcon.Right) {
            when(icon.icon) {
                is IconType.VectorIcon -> {
                    Icon(
                        imageVector = icon.icon.imageVector,
                        contentDescription = icon.icon.iconDescription,
                        modifier = Modifier.size(textStyle.fontSize.value.dp)
                    )
                }
                is IconType.PainterIcon -> {
                    Icon(
                        painter = icon.icon.painter,
                        contentDescription = icon.icon.iconDescription,
                        modifier = Modifier.size(textStyle.fontSize.value.dp)
                    )
                }
            }
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



data class Item(
    val name: String,
    val price: Float,
    @DrawableRes val imageRes: Int, // Référence de l'image
    val noteContent: String? = null // Contenu de la note (peut être null)
)
