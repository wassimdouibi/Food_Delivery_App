package com.example.food_delivery_app.core.components

import com.example.food_delivery_app.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.core.home.model.entity.Restaurant
import com.example.food_delivery_app.ui.theme.Colors.defaultCustomColorScheme
import com.example.food_delivery_app.ui.theme.Typography.defaultCustomTypographyScheme


@Composable
fun RestaurantInfo(restaurant: Restaurant, onClose: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            //.background(Color.Red)
            .padding(horizontal = 16.dp , vertical = 32.dp)
    ) {
        // Close button
        IconButton(
            onClick = { onClose() },
            modifier = Modifier.align(Alignment.End)
        ) {
            Image(
              painter = painterResource(id = R.drawable.ic_close)  ,
              contentDescription = "close icon",
              modifier = Modifier
                    .size(40.dp)
            )
        }
        Spacer(Modifier.height(16.dp))

        // Restaurant details
        Text(
            text = "More Info",
            style = defaultCustomTypographyScheme.heading4,
            //modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(Modifier.height(32.dp))


        InfoRow(iconPainter = painterResource( id = R.drawable.ic_location), text = restaurant.address)
        InfoRow(iconPainter = painterResource( id = R.drawable.ic_rating), text = "${restaurant.averageRating} Stars" , textSemiBold = " (${restaurant.reviewCount} reviews)")
        InfoRow(iconPainter = painterResource( id = R.drawable.ic_phone), text = restaurant.contactPhone)
        InfoRow(iconPainter = painterResource( id = R.drawable.ic_contact), text = restaurant.contactEmail)
        InfoRow(iconPainter = painterResource( id = R.drawable.ic_instagram), text = restaurant.instaLink ?: "")
        InfoRow(iconPainter = painterResource( id = R.drawable.ic_facebook), text = restaurant.fbLink ?: "")

    }
}

@Composable
fun InfoRow(iconPainter: Painter , text: String, textSemiBold: String = "") {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = iconPainter,
            contentDescription = null,
            modifier = Modifier
                .size(34.dp)

        )

        Spacer(Modifier.width(16.dp))

        Text(text = text, style = defaultCustomTypographyScheme.p_medium, color = defaultCustomColorScheme.ink500)
        Text(text = textSemiBold, style = defaultCustomTypographyScheme.p_smallSemiBold, color = defaultCustomColorScheme.ink300)

    }
}