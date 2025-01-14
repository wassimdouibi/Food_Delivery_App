package com.example.food_delivery_app.core.orders.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.food_delivery_app.core.components.IncrementDecrementRow
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import kotlinx.coroutines.launch

@Composable
fun AddToCardOrder(
    price: Float, // Prix pour ShopItemRow
    initialValue: Int = 0,// Valeur initiale pour IncrementDecrementRow
    addOrder: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()

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
                scope.launch {
                    addOrder()
                }
            },
            cardColor = LocalCustomColorScheme.current.primary400
        )
    }
}
