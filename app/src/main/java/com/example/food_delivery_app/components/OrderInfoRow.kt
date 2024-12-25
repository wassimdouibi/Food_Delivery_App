package com.example.food_delivery_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme


@Composable
fun OrderInfoRow(
    label: String,
    content: String,

    icon: IconType,

    contentColor: Color,
    containerColor: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Icône personnalisée remplacée par RoundedIconBtn
        when (icon) {
            is IconType.VectorIcon -> {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(containerColor, shape = CircleShape)
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon.imageVector,
                        contentDescription = icon.iconDescription,
                        tint = contentColor,
                        modifier = Modifier
                    )
                }
            }
            is IconType.PainterIcon -> {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(containerColor, shape = CircleShape)
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        painter = icon.painter,
                        contentDescription = icon.iconDescription,
                        tint = contentColor,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Texte avec informations
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = label,
                style = LocalCustomTypographyScheme.current.p_small,
                color = LocalCustomColorScheme.current.ink400
            )
            Text(
                text = content,
                style = LocalCustomTypographyScheme.current.p_mediumBold.copy(
                    fontFamily = FontFamily.Default
                ),
                color = LocalCustomColorScheme.current.ink500
            )
        }
    }
}

