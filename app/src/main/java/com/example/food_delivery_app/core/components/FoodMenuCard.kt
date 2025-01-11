package com.example.food_delivery_app.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.core.home.model.services.response.FoodResponse
import com.example.food_delivery_app.core.home.model.services.response.RestaurantResponse
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme
import com.example.food_delivery_app.ui.theme.Colors.defaultCustomColorScheme
import com.example.food_delivery_app.ui.theme.Typography.defaultCustomTypographyScheme




@Composable
fun FoodMenuCard(
    restaurantResponse : RestaurantResponse,
    foodResponse: FoodResponse,
    navController: NavController,
    modifier : Modifier = Modifier
        .width(320.dp)
        .clickable {

        }
){
    Column(
        modifier = modifier ,
    ) {
        CardImageSection( pictures = restaurantResponse.pics, rating = restaurantResponse.restaurant.averageRating)

        Spacer(modifier = Modifier.height(8.dp))

        // Text and Details Section
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()

        ){
            Column {
                Text(
                    text = foodResponse.food.name,
                    style = defaultCustomTypographyScheme.heading5,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.dollar),
                        contentDescription = "Location Icon",
                        modifier = Modifier.size(20.dp),
                        colorFilter = ColorFilter.tint(defaultCustomColorScheme.primary400)
                    )
                    Spacer(modifier = Modifier.width(2.dp))

                    Text(
                        text = " ${foodResponse.food.price} DZD",
                        style = defaultCustomTypographyScheme.p_small,
                        color = defaultCustomColorScheme.primary400
                    )
                }
                Spacer(modifier = Modifier.height(3.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location Icon",
                        tint = defaultCustomColorScheme.primary400,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))

                    Text(
                        text = " ${restaurantResponse.restaurant.name}, ${restaurantResponse.restaurant.address}",
                        style = defaultCustomTypographyScheme.p_small,
                        color = defaultCustomColorScheme.primary400
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))




            }
            FilledTextButton(
                onClick = {},
                textContent = "Order",
                textStyle = LocalCustomTypographyScheme.current.p_mediumBold,
                icon = ButtonIcon.Right(
                    IconType.VectorIcon(
                        imageVector = Icons.Filled.Info,
                        iconDescription = "Info"
                    )
                ),
                modifier = Modifier.clip(
                    RoundedCornerShape(2.dp)
                )

            )

        }

        //Spacer(modifier = Modifier.height(8.dp))

        // info Section
        Text(
            text= "Info",
            style = defaultCustomTypographyScheme.p_smallSemiBold
        )

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text= foodResponse.food.description!!,
            style = defaultCustomTypographyScheme.p_small,
            color = defaultCustomColorScheme.ink400,
            lineHeight = 20.sp,
            textAlign = TextAlign.Start
        )
    }
}