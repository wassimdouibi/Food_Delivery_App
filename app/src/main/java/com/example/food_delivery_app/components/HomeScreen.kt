package com.example.food_delivery_app.components

import com.example.food_delivery_app.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.food_delivery_app.ui.theme.defaultCustomColorScheme
import com.example.food_delivery_app.ui.theme.defaultCustomTypographyScheme


data class Cuisine(
    val name: String,
    val imageRes: Int
)

@Composable
fun HomeScreen(
    cuisines: List<Cuisine>,
    categories: List<Cuisine>,
    restaurants: List<Restaurant>,
    navController : NavHostController
) {
    var search by remember { mutableStateOf("") }
    var showFilterState by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Search Bar
            FoodDeliveryTextField(
                value = search,
                onValueChange = { search = it },
                placeHolderText = stringResource(R.string.input_search_restaurant),
                leadingIconVector = Icons.Default.Search,
                trailingIconId = R.drawable.ri_equalizer_fill,
                modifier = Modifier.fillMaxWidth(.9f).align(Alignment.CenterHorizontally),
                changeShowFilterState = { showFilterState = !showFilterState}
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Cuisines Section
            SectionTitle(title = "Cuisines")

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(cuisines) { cuisine ->
                    CuisineIcon(
                        cuisine = cuisine,
                        onClick = {  }
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            // Top Categories Section
            SectionTitle(title = "Top Categories")

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(categories) { category ->
                    CuisineIcon(
                        cuisine = category,
                        onClick = {  }
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            // Top Restaurants Section
            SectionTitle(title = "Top Restaurants")

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(restaurants) { restaurant ->
                    RestaurantCard(
                        restaurant = restaurant,
                        onDetailsClick = {} // Pass NavController for navigation
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            SectionTitle(title = "Near Restaurants")

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(restaurants) { restaurant ->
                    RestaurantCard(
                        restaurant = restaurant,
                        onDetailsClick = {} // Pass NavController for navigation
                    )
                }
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = defaultCustomTypographyScheme.heading4,
        color = defaultCustomColorScheme.ink500,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun CuisineIcon(cuisine: Cuisine, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(90.dp)
            //.padding( vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = cuisine.imageRes),
            contentDescription = cuisine.name,
            modifier = Modifier
                .width(90.dp)
                .height(80.dp)
                .clip(RoundedCornerShape(8.dp)) ,
         contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = cuisine.name,
            style = defaultCustomTypographyScheme.p_smallSemiBold,
            color = defaultCustomColorScheme.ink500
        )
    }
}



// Example usage
@Composable
fun PreviewHomeScreen() {
    val cuisines = listOf(
        Cuisine("Algerian", R.drawable.img_food_one),
        Cuisine("Italian", R.drawable.img_food_two),
        Cuisine("Japanese", R.drawable.img_food_three),
        Cuisine("French", R.drawable.img_food_one)
    )

    val categories = listOf(
        Cuisine("Vegan", R.drawable.img_food_three),
        Cuisine("Dessert", R.drawable.img_food_two),
        Cuisine("Seafood", R.drawable.img_food_one),
        Cuisine("Snacks", R.drawable.img_food_three)
    )

    val restaurants = listOf(
        Restaurant(
            name = "Gourmet Spot",
            location = "City Center",
            rating = 4.8,
            reviews = 220,
            phone = "123-456-7891",
            email = "contact@gourmetspot.com",
            instagram = "@gourmetspot",
            facebook = "GourmetSpot",
            locationImage = R.drawable.img_map_location,
            restaurantPictures = listOf(R.drawable.img_food_one, R.drawable.img_food_two, R.drawable.img_food_three),
            topPicks = listOf("Pasta", "Seafood")
        ),
        Restaurant(
            name = "Gourmet Spot",
            location = "City Center",
            rating = 4.8,
            reviews = 220,
            phone = "123-456-7891",
            email = "contact@gourmetspot.com",
            instagram = "@gourmetspot",
            facebook = "GourmetSpot",
            locationImage = R.drawable.img_map_location,
            restaurantPictures = listOf(R.drawable.img_food_one, R.drawable.img_food_two, R.drawable.img_food_three),
            topPicks = listOf("Pasta", "Seafood")
        )
    )

    HomeScreen(
        cuisines = cuisines,
        categories = categories,
        restaurants = restaurants,
        navController = rememberNavController()
    )
}
