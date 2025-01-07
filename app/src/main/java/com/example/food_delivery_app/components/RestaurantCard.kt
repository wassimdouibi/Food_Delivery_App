package com.example.food_delivery_app.components

import com.example.food_delivery_app.R
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.Modifier.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.food_delivery_app.ui.theme.*


@Composable
fun RestaurantCard(
    restaurant: Restaurant,
    navController: NavController // Callback for "Details" button
) {
    Column(
        modifier = Modifier
            .width(320.dp)
        //.padding(16.dp)
        /*.background(
            color = Color.White,
            //shape = RoundedCornerShape(8.dp)
        )*/
    ) {
        CardImageSection( pictures = restaurant.restaurantPictures, rating = restaurant.rating)

        Spacer(modifier = Modifier.height(8.dp))

        // Text and Details Section
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()

        ){
            Column {
                Text(
                    text = restaurant.name,
                    style = defaultCustomTypographyScheme.heading5,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location Icon",
                        tint = defaultCustomColorScheme.primary400,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = restaurant.location,
                        style = defaultCustomTypographyScheme.p_small,
                        color = defaultCustomColorScheme.primary400
                    )
                }

            }
            FilledTextButton(
                onClick = {  },
                textContent = "Details",
                textStyle = LocalCustomTypographyScheme.current.p_mediumBold,
                icon = ButtonIcon.Right(
                    IconType.VectorIcon(
                        imageVector = Icons.Default.Info,
                        iconDescription = "Info"
                    )
                ),
                modifier = Modifier.clip(RoundedCornerShape(2.dp))
            )

        }

        //Spacer(modifier = Modifier.height(8.dp))

        // Top Picks Section
        Text(
            text= "Top Picks",
            style = defaultCustomTypographyScheme.p_medium
        )

        Spacer(modifier = Modifier.height(4.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            //contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(restaurant.topPicks ?: emptyList()) { pick ->
                Box(
                    modifier = Modifier
                        .background(
                            color = Color.White,
                            //shape = RoundedCornerShape(16.dp)
                        )
                        .border(width = 2.dp, color = defaultCustomColorScheme.ink300)
                        .padding(horizontal = 16.dp, vertical = 8.dp)

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


@Composable
fun CardImageSection(pictures : List<Int> , rating: Double){
    // Image Section
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)

    ) {
        // Left: Main Image
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(end = 2.dp)

        ) {
            Image(
                painter = painterResource(id = pictures[0]), // First image
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                //.height(200.dp)
                //.clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            // Star Rating
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    //.padding(8.dp)
                    .background(
                        color = Color(0xFFFFA500),
                        shape = RoundedCornerShape(4.dp)
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
                        style = defaultCustomTypographyScheme.p_mediumBold
                    )
                }
            }
        }
        // Right: Two Small Images
        Column(
            modifier = Modifier.weight(1f)
        ) {
            pictures.drop(1).forEachIndexed { index, imageRes ->
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(vertical = if (index == 0) 0.dp else 2.dp),

                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}


@Composable
fun PreviewRestaurantCard() {
    val restaurants =
        restaurant1
    RestaurantCard(restaurant = restaurants, navController = rememberNavController())
}