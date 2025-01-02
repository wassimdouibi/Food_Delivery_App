package com.example.food_delivery_app.components

import com.example.food_delivery_app.R
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.food_delivery_app.ui.theme.defaultCustomColorScheme
import com.example.food_delivery_app.ui.theme.defaultCustomTypographyScheme


data class Restaurant(
    val name: String,
    val location: String,
    val rating: Double,
    val reviews: Int,
    val phone: String,
    val email: String,
    val instagram: String,
    val facebook: String,
    val painterRes : Int
)

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
        // map
        Box(

            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp)
                .clip(RoundedCornerShape(14.dp)).background(Color.Red)


        ) {
            Image(
                painter = painterResource( restaurant.painterRes),
                contentDescription = "Restaurant Location image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop

            )
        }



        InfoRow(iconPainter = painterResource( id = R.drawable.ic_location), text = restaurant.location)
        InfoRow(iconPainter = painterResource( id = R.drawable.ic_rating), text = "${restaurant.rating} Stars" , textSemiBold = " (${restaurant.reviews} reviews)")
        InfoRow(iconPainter = painterResource( id = R.drawable.ic_phone), text = restaurant.phone)
        InfoRow(iconPainter = painterResource( id = R.drawable.ic_contact), text = restaurant.email)
        InfoRow(iconPainter = painterResource( id = R.drawable.ic_instagram), text = restaurant.instagram)
        InfoRow(iconPainter = painterResource( id = R.drawable.ic_facebook), text = restaurant.facebook)

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

@Preview(showBackground = true)
@Composable
fun PreviewRestaurantInfo() {
    val restaurant = Restaurant(
        name = "Hicham Cook",
        location = "Jl. Soekarno Hatta 15A Malang",
        rating = 4.5,
        reviews = 1200,
        phone = "+213 540804694",
        email = "hichamcook@gmail.com",
        instagram = "hicham_cook",
        facebook = "hicham.cook",
        painterRes = R.drawable.img_map_location
    )

    RestaurantInfo(restaurant = restaurant, onClose = {})
}
