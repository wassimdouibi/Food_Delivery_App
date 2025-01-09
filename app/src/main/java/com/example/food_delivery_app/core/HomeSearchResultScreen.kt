package com.example.food_delivery_app.core

import com.example.food_delivery_app.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.food_delivery_app.components.BottomBar
import com.example.food_delivery_app.components.Cuisine
import com.example.food_delivery_app.components.FoodDeliveryTextField
import com.example.food_delivery_app.components.Restaurant
import com.example.food_delivery_app.components.RestaurantCard
import com.example.food_delivery_app.components.categoriesList
import com.example.food_delivery_app.components.cuisinesList
import com.example.food_delivery_app.components.restaurant1
import com.example.food_delivery_app.components.restaurant2
import com.example.food_delivery_app.components.restaurant3
import com.example.food_delivery_app.core.Home.SectionTitle
import com.example.food_delivery_app.ui.theme.defaultCustomColorScheme
import com.example.food_delivery_app.ui.theme.defaultCustomTypographyScheme

/* ce qui reste : reasearch result page , which will be the result of the clicking
* on the cuisine and categories cards as well as reasearch */


@Composable
fun HomeSearchResultScreen(
    navController: NavController,
    initialSearchInput: String,
    restaurantList: List<Restaurant> // Full dataset of restaurants
) {
    // State to manage the search input and dynamically filter results
    var searchInput by remember { mutableStateOf(initialSearchInput) }
    var filteredRestaurants by remember { mutableStateOf(searchRestaurants(searchInput, restaurantList)) }

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Search Bar
            FoodDeliveryTextField(
                value = searchInput,
                onValueChange = {
                    searchInput = it
                    filteredRestaurants = searchRestaurants(searchInput, restaurantList)
                },
                placeHolderText = stringResource(R.string.input_search_restaurant),
                leadingIconVector = Icons.Default.Search,
                trailingIconId = R.drawable.ri_equalizer_fill,
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .align(Alignment.CenterHorizontally),
                changeShowFilterState = { /* Handle filter state if needed */ }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Search Result Section
            SectionTitle(title = "Search Results")

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (filteredRestaurants.isEmpty()) {
                    Text(
                        text = "No Results Found",
                        style = defaultCustomTypographyScheme.p_large,
                        color = defaultCustomColorScheme.ink400,
                        modifier = Modifier.padding(16.dp) )
                } else {
                    filteredRestaurants.forEach { restaurant ->
                        RestaurantCard(navController = navController, restaurant = restaurant)
                    }
                }
            }
        }
    }
}


fun searchRestaurants(input: String, restaurants: List<Restaurant>): List<Restaurant> {
    return restaurants.filter { restaurant ->
        restaurant.name.contains(input, ignoreCase = true)
               // || restaurant.cuisine.name.contains(input, ignoreCase = true)
    }
}


// Example usage
@Composable
fun PreviewHomeSearchResultScreen() {

    HomeSearchResultScreen(
        navController = rememberNavController() ,
        initialSearchInput = "El Bahja Food",
        restaurantList = listOf( restaurant1, restaurant2, restaurant3) )


}
