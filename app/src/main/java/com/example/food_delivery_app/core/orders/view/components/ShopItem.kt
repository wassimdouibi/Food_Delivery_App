package com.example.food_delivery_app.core.orders.view.components


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.R
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme

@SuppressLint("DefaultLocale")
@Composable
fun ShopItem(
    modifier: Modifier = Modifier,
    onClick: ()->Unit, // Action déclenchée par le bouton

    price: Float,
    cardColor: Color
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(4.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically, // Alignement vertical centré
            horizontalArrangement = Arrangement.SpaceBetween // Espacement total dans une seule ligne
        ) {
            // Bouton avec texte
            Text(
                text = stringResource(R.string.cta_add_to_cart),
                style = LocalCustomTypographyScheme.current.p_mediumBold,
                color = LocalCustomColorScheme.current.ink50
            )
            Spacer(modifier = Modifier.weight(1f))
            // Icône de shopping cart
            Icon(
                painter = painterResource(R.drawable.orders), // Icône de Material Icons
                contentDescription = "Shop Icon", // Description pour l'accessibilité
                tint = LocalCustomColorScheme.current.ink50,
            )
            Spacer(modifier = Modifier.weight(2f))
            // Affichage du prix
            Text(
                text = String.format("%.1f DZD", price),
                style = LocalCustomTypographyScheme.current.p_mediumBold.copy(
                    fontFamily = FontFamily.Default
                ),
                color = LocalCustomColorScheme.current.ink50
            )
        }
    }
}
