package com.example.food_delivery_app.core

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.components.CustomerReview
import com.example.food_delivery_app.components.FoodDeliveryTextField
import com.example.food_delivery_app.components.FoodMenuCard
import com.example.food_delivery_app.components.InfoRow
import com.example.food_delivery_app.components.PreviewCustomerReview
import com.example.food_delivery_app.components.Restaurant
import com.example.food_delivery_app.components.RestaurantCard
import com.example.food_delivery_app.components.restaurant1
import com.example.food_delivery_app.ui.theme.defaultCustomColorScheme
import com.example.food_delivery_app.ui.theme.defaultCustomTypographyScheme



@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RestaurantDetailsScreen(
    navController : NavController,
    restaurant: Restaurant
) {
    var isFavorite by remember { mutableStateOf(false) }
    val mealTypes = listOf("All", "Breakfast", "Lunch & Dinner", "Desserts", "Drinks")
    var selectedMealType by remember { mutableStateOf(mealTypes[0]) } // Initially, the first meal type is selected
    var filteredFoodList by remember { mutableStateOf(restaurant.foodList) }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
            // .padding(end = 2.dp)

        ) {
            Image(
                painter = painterResource(id = restaurant.restaurantPictures[0]), // First image
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                //.height(200.dp)
                //.clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            IconButton(
                onClick = { isFavorite = !isFavorite },
                modifier = Modifier
                    .align(Alignment.TopEnd) // Position icon in the top right
                    .padding(8.dp) // Space from the edge
            ) {
                // Use Favorite or FavoriteBorder icon based on the state
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) defaultCustomColorScheme.primary500 else defaultCustomColorScheme.ink300 // Change color based on state
                )
            }

        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // name section
            Text(
                text = restaurant.name,
                style = defaultCustomTypographyScheme.heading3,
                color = defaultCustomColorScheme.ink500
            )

            Text(
                text = "More Info",
                style = defaultCustomTypographyScheme.p_mediumBold,
                color = defaultCustomColorScheme.ink400,
                modifier = Modifier.clickable { }  // add navigation to the restaurant details component
            )
        }

        //location Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_location),
                contentDescription = null,
                modifier = Modifier
                    .size(34.dp)

            )

            Spacer(Modifier.width(16.dp))

            Text(
                text = restaurant.location,
                style = defaultCustomTypographyScheme.p_smallBold,
                color = defaultCustomColorScheme.ink500
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Known For Section
        SectionTitle(title = "Known For")
        FlowRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),

            ) {
            restaurant.topPicks?.forEachIndexed { index, pick ->
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .border(width = 2.dp, color = defaultCustomColorScheme.ink300)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = pick,
                        style = defaultCustomTypographyScheme.p_small,
                        color = defaultCustomColorScheme.ink400
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        //Categories section
        SectionTitle(title = "Categories")

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp) // Equal spacing between items
        ) {
            items(mealTypes) { mealType ->
                val isSelected = mealType == selectedMealType
                Text(
                    text = mealType,
                    style = defaultCustomTypographyScheme.p_mediumBold,
                    color = if (isSelected) defaultCustomColorScheme.primary400 else defaultCustomColorScheme.ink400,
                    modifier = Modifier
                        .clickable {
                            selectedMealType =
                                mealType // Update the selected meal type when clicked
                        }
                        .padding(horizontal = 16.dp) // Optional padding around the text
                )
            }
        }

        /**************************Food Section *******************************///
        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Filter the food list based on the selected category
            val foodItemsToDisplay = if (selectedMealType == "All") {
                restaurant.foodList
            } else {
                restaurant.foodList.filter { food -> food.category == selectedMealType }
            }

            items(foodItemsToDisplay) { food ->
                FoodMenuCard(
                    restaurant = restaurant,
                    food = food,
                    navController = navController
                )
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        SectionTitle(title = "Reviews")

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            restaurant.reviewsList?.forEach { review ->
                CustomerReview(
                    customerReview = review
                )
            }

        }

    }
}


@Composable
fun previewRestDetailsScreen ()
    {
    val restaurant : Restaurant = restaurant1

    RestaurantDetailsScreen(
    navController = rememberNavController(),
    restaurant = restaurant
    )
}