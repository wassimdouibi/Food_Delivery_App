package com.example.food_delivery_app.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme


@SuppressLint("DefaultLocale")
@Composable
fun ItemInfoRow(
    text1: String,
    price: Float,
    imageRes1: Int,
    buttonContentColor: Color = Color.White,
    buttonContainerColor: Color = Color.Red,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(id = imageRes1),
            contentDescription = "Item Image",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.weight(.2f))

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = text1,
                style = LocalCustomTypographyScheme.current.p_medium,
                color = LocalCustomColorScheme.current.ink400
            )
            Text(
                text = String.format("%.2f", price) + " DZD",
                style = LocalCustomTypographyScheme.current.p_mediumBold.copy(
                    fontFamily = FontFamily.Default
                ),
                color = LocalCustomColorScheme.current.ink500
            )
        }

        Spacer(modifier = Modifier.weight(.8f))

        // Bouton rond utilisant RoundedIconBtn avec l'icône Delete directement
        RoundedIconBtn(
            onClick = onDeleteClick,
            icon = Icons.Default.Delete, // Icône Delete directement ici
            iconDescription = "Delete Icon",
            contentColorEnabled = buttonContentColor,
            containerColorEnabled = buttonContainerColor
        )

    }
}