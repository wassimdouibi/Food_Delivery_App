package com.example.food_delivery_app.core.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme

@Composable
fun AddToCardOrder(
    price: Float, // Prix pour ShopItemRow
    initialValue: Int = 0,// Valeur initiale pour IncrementDecrementRow
    addOrder: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween// Alignement vertical centré
    ) {
        // Composant IncrementDecrementRow avec poids
        IncrementDecrementRow(
            initialValue = initialValue,
            modifier = Modifier.weight(.25f)
        )
        Spacer(modifier = Modifier.weight(.1f))
        // Composant ShopItem avec poids
        ShopItem(
            modifier = modifier.weight(.65f),
            price=price,
            onClick = {
                addOrder()
            },
            cardColor = LocalCustomColorScheme.current.primary400
        )
    }
}
