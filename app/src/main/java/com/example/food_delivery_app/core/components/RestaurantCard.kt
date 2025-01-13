package com.example.food_delivery_app.core.components

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.core.home.model.services.response.RestaurantResponse
import com.example.food_delivery_app.router.Router
import com.example.food_delivery_app.ui.theme.*
import com.example.food_delivery_app.ui.theme.Colors.defaultCustomColorScheme
import com.example.food_delivery_app.ui.theme.Typography.defaultCustomTypographyScheme
import com.google.gson.Gson


@Composable
fun RestaurantCard(restaurant: RestaurantResponse, navController: NavController) {
    Card(
        modifier = Modifier.width(300.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(0.dp)
    ) {
        CardImageSection( pictures = restaurant.pics, rating = restaurant.restaurant.averageRating)

        Spacer(modifier = Modifier.height(16.dp))

        // Text and Details Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Restaurant Name
                Text(
                    text = restaurant.restaurant.name,
                    style = LocalCustomTypographyScheme.current.heading5,
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Restaurant Location
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location Icon",
                        tint = defaultCustomColorScheme.primary400,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = restaurant.restaurant.address,
                        style = defaultCustomTypographyScheme.p_small,
                        color = defaultCustomColorScheme.primary400,
                        minLines = 2
                    )
                }

            }

            // Details Button
            FilledTextButton(
                onClick = {
                    navController.navigate(
                        Router.RestaurantDetailsScreen.createRoute(restaurant.restaurant.restaurantId)
                    )
                },
                textContent = "Details",
                textStyle = LocalCustomTypographyScheme.current.p_mediumBold,

                icon = ButtonIcon.Right(
                    IconType.VectorIcon(
                        imageVector = Icons.Default.Info,
                        iconDescription = "Info"
                    )
                ),
                modifier = Modifier.width(120.dp),
                containerColor = LocalCustomColorScheme.current.primary500
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Top Picks Section
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text= "Top Picks",
                style = defaultCustomTypographyScheme.p_medium
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(restaurant.tags) { pick ->
                    Box(
                        modifier = Modifier
                            .border(width = 1.dp, color = defaultCustomColorScheme.ink300)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                    {
                        Text(
                            text = pick,
                            style = defaultCustomTypographyScheme.p_small,
                            color = defaultCustomColorScheme.ink400
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun CardImageSection(
    pictures: List<String>,
    rating: Float
){
    // Image Section
    Row(
        modifier = Modifier.fillMaxWidth().height(200.dp)
    ) {
        // Left: Main Image
        Box(modifier = Modifier.weight(1f)) {
            Image(
                painter = painterResource(id = R.drawable.img_restaurant_1), // First image
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            // Star Rating
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .background(
                        color = LocalCustomColorScheme.current.primary400,
                        shape = RoundedCornerShape(topEnd = 6.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star Icon",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = rating.toString(),
                        color = Color.White,
                        style = defaultCustomTypographyScheme.p_medium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(2.dp))

        // Right: Two Small Images
        Column(modifier = Modifier.weight(1f)) {
            Image(
                painter = painterResource(id = R.drawable.img_restaurant_2), // First image
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(2.dp))
            Image(
                painter = painterResource(id = R.drawable.img_restaurant_2), // First image
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentScale = ContentScale.Crop
            )
        }
    }
}