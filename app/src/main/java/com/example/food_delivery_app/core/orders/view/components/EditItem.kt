package com.example.food_delivery_app.core.orders.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.core.components.RectangularIconBtn
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme


@Composable
fun EditItem(
    text: String,
    onClick: () -> Unit,
    cardColor: Color,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(cardColor),
        shape = RoundedCornerShape(4.dp),

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                style = LocalCustomTypographyScheme.current.p_mediumBold,
                color = LocalCustomColorScheme.current.ink400
            )

            RectangularIconBtn(
                onClick = onClick,

                icon = Icons.Default.Add,
                iconDescription = "Edit Item Button",

                contentColor = LocalCustomColorScheme.current.ink50,
                containerColor = LocalCustomColorScheme.current.primary400,
                size = 32.dp
            )
        }
    }
}