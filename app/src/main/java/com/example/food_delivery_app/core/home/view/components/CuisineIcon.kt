package com.example.food_delivery_app.core.home.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.core.home.model.entity.Category
import com.example.food_delivery_app.core.home.model.entity.CuisineType
import com.example.food_delivery_app.router.Router
import com.example.food_delivery_app.ui.theme.Colors.defaultCustomColorScheme
import com.example.food_delivery_app.ui.theme.Typography.defaultCustomTypographyScheme

@Composable
fun CuisineIcon(
    cuisineType: CuisineType,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.wrapContentWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.pizza),
            contentDescription = cuisineType.name,
            modifier = Modifier
                .width(90.dp)
                .height(80.dp)
                .clip(RoundedCornerShape(6.dp))
                .clickable {

                },
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = cuisineType.name,
            style = defaultCustomTypographyScheme.p_smallSemiBold,
            color = defaultCustomColorScheme.ink500
        )
    }
}


@Composable
fun CuisineIcon(
    cuisineType: Category
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.wrapContentWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.pizza),
            contentDescription = cuisineType.name,
            modifier = Modifier
                .width(90.dp)
                .height(80.dp)
                .clip(RoundedCornerShape(6.dp))
                .clickable {

                },
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = cuisineType.name,
            style = defaultCustomTypographyScheme.p_smallSemiBold,
            color = defaultCustomColorScheme.ink500
        )
    }
}