package com.example.food_delivery_app.core.home.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.R
import com.example.food_delivery_app.core.components.Cuisine
import com.example.food_delivery_app.core.home.model.entity.Category
import com.example.food_delivery_app.core.home.model.entity.CuisineType
import com.example.food_delivery_app.ui.theme.Colors.defaultCustomColorScheme
import com.example.food_delivery_app.ui.theme.Typography.defaultCustomTypographyScheme

@Composable
fun CuisineIcon(cuisineType: CuisineType, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(90.dp)
            //.padding( vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_wavy_buddies_preparing_your_food),
            contentDescription = cuisineType.name,
            modifier = Modifier
                .width(90.dp)
                .height(80.dp)
                .clip(RoundedCornerShape(8.dp)) ,
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = cuisineType.name,
            style = defaultCustomTypographyScheme.p_smallSemiBold,
            color = defaultCustomColorScheme.ink500
        )
    }
}

@Composable
fun CuisineIcon(cuisineType: Category, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(90.dp)
            //.padding( vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_wavy_buddies_preparing_your_food),
            contentDescription = cuisineType.name,
            modifier = Modifier
                .width(90.dp)
                .height(80.dp)
                .clip(RoundedCornerShape(8.dp)) ,
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = cuisineType.name,
            style = defaultCustomTypographyScheme.p_smallSemiBold,
            color = defaultCustomColorScheme.ink500
        )
    }
}