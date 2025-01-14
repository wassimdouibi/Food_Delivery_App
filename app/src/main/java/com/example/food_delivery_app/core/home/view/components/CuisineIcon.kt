package com.example.food_delivery_app.core.home.view.components

import android.util.Log
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
import coil.compose.AsyncImage
import com.example.food_delivery_app.R
import com.example.food_delivery_app.core.home.model.entity.Category
import com.example.food_delivery_app.core.home.model.entity.CuisineType
import com.example.food_delivery_app.router.Router
import com.example.food_delivery_app.ui.theme.Colors.defaultCustomColorScheme
import com.example.food_delivery_app.ui.theme.Typography.defaultCustomTypographyScheme
import org.jetbrains.annotations.Async

val cuisineTypeUrls = listOf(
    "https://images.pexels.com/photos/2232/vegetables-italian-pizza-restaurant.jpg", // Italian
    "https://images.pexels.com/photos/1435907/pexels-photo-1435907.jpeg", // Chinese
    "https://images.pexels.com/photos/461198/pexels-photo-461198.jpeg", // Mexican
    "https://images.pexels.com/photos/70497/pexels-photo-70497.jpeg", // Japanese
    "https://images.pexels.com/photos/102104/pexels-photo-102104.jpeg", // French
    "https://images.pexels.com/photos/539451/pexels-photo-539451.jpeg",
    "https://images.pexels.com/photos/539451/pexels-photo-539451.jpeg",
    "https://images.pexels.com/photos/539451/pexels-photo-539451.jpeg",
    "https://images.pexels.com/photos/539451/pexels-photo-539451.jpeg",
)




@Composable
fun CuisineIcon(
    index: Int,
    cuisineType: CuisineType,
    navController: NavController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.wrapContentWidth()
    ) {
        AsyncImage(
            model = cuisineTypeUrls[index],
            contentDescription = cuisineType.name,
            modifier = Modifier
                .width(90.dp)
                .height(80.dp)
                .clip(RoundedCornerShape(6.dp))
                .clickable {
                    navController.navigate(Router.CuisineRestaurantsScreen.createRoute(cuisineType.name))
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


val categoryUrls = listOf(
    "https://images.pexels.com/photos/70497/pexels-photo-70497.jpeg", // Fast Food
    "https://images.pexels.com/photos/1132558/pexels-photo-1132558.jpeg", // Desserts
    "https://images.pexels.com/photos/1132558/pexels-photo-1132558.jpeg", // Beverages
    "https://images.pexels.com/photos/286215/pexels-photo-286215.jpeg", // Seafood
    "https://images.pexels.com/photos/1640777/pexels-photo-1640777.jpeg", // Vegetarian
    "https://images.pexels.com/photos/539451/pexels-photo-539451.jpeg",
    "https://images.pexels.com/photos/539451/pexels-photo-539451.jpeg",
    "https://images.pexels.com/photos/539451/pexels-photo-539451.jpeg",
    "https://images.pexels.com/photos/539451/pexels-photo-539451.jpeg",
)


@Composable
fun CuisineIcon(
    index: Int,
    cuisineType: Category,
    navController: NavController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.wrapContentWidth()
    ) {
        AsyncImage(
            model = categoryUrls[index],
            contentDescription = cuisineType.name,
            modifier = Modifier
                .width(90.dp)
                .height(80.dp)
                .clip(RoundedCornerShape(6.dp))
                .clickable {
                    navController.navigate(Router.FoodCategoryScreen.createRoute(cuisineType.name))
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