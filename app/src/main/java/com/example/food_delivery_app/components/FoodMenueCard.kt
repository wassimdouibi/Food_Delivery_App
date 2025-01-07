package com.example.food_delivery_app.components

import android.widget.RemoteViews
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.ui.theme.defaultCustomColorScheme
import com.example.food_delivery_app.ui.theme.defaultCustomTypographyScheme


/* ce qui manque :
* menue card finalisation
*  affichage menue selon le mealtype
* reviews affichage selon restaurant*/


@Composable
fun FoodMenuCard(
    restaurant : Restaurant, food: Food ,
    navController: NavController,
    modifier : Modifier = Modifier
        .width(320.dp)
        .clickable { }
    //.padding(16.dp)
    /*.background(
        color = Color.White,
        //shape = RoundedCornerShape(8.dp)
    )*/
){
    Column(
        modifier = modifier ,
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
                    text = food.name,
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
                        text = " ${food.price} DZD",
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
                        text = " ${restaurant.name}, ${restaurant.location}",
                        style = defaultCustomTypographyScheme.p_small,
                        color = defaultCustomColorScheme.primary400
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))




            }
            FilledTextButtonWithIcon(
                onClick = {},
                textContent = "Order",
                icon = Icons.Filled.Info,  // Use any icon you prefer
                iconDescription = "info",
                shape = RoundedCornerShape(2.dp)

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
            text= food.info,
            style = defaultCustomTypographyScheme.p_small,
            color = defaultCustomColorScheme.ink400,
            lineHeight = 20.sp,
            textAlign = TextAlign.Start
        )
    }
}