//package com.example.food_delivery_app.core
//
//import com.example.food_delivery_app.R
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import com.example.food_delivery_app.core.Home.View.components.SectionTitle
//import com.example.food_delivery_app.core.Restaurants.Model.entity.Restaurant
//import com.example.food_delivery_app.core.components.FoodDeliveryTextField
//import com.example.food_delivery_app.favorites.components.RestaurantCard
//import com.example.food_delivery_app.ui.theme.Colors.defaultCustomColorScheme
//import com.example.food_delivery_app.ui.theme.Typography.defaultCustomTypographyScheme
//
//
//@Composable
//fun HomeSearchResultView(
//    navController: NavController,
//    initialSearchInput: String,
//    restaurantList: List<Restaurant> // Full dataset of restaurants
//) {
//    // State to manage the search input and dynamically filter results
//    var searchInput by remember { mutableStateOf(initialSearchInput) }
//    var filteredRestaurants by remember { mutableStateOf(searchRestaurants(searchInput, restaurantList)) }
//
//    Column(
//        modifier = Modifier
//            .verticalScroll(rememberScrollState())
//            .fillMaxSize()
//    ) {
//        // Search Bar
//        FoodDeliveryTextField(
//            value = searchInput,
//            onValueChange = {
//                searchInput = it
//                filteredRestaurants = searchRestaurants(searchInput, restaurantList)
//            },
//            placeHolderText = stringResource(R.string.input_search_restaurant),
//            leadingIconVector = Icons.Default.Search,
//            trailingIconId = R.drawable.ri_equalizer_fill,
//            modifier = Modifier
//                .fillMaxWidth(.9f)
//                .align(Alignment.CenterHorizontally),
//            changeShowFilterState = { /* Handle filter state if needed */ }
//        )
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        // Search Result Section
//        SectionTitle(title = "Search Results")
//
//        Column(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            if (filteredRestaurants.isEmpty()) {
//                Text(
//                    text = "No Results Found",
//                    style = defaultCustomTypographyScheme.p_large,
//                    color = defaultCustomColorScheme.ink400,
//                    modifier = Modifier.padding(16.dp) )
//            } else {
//                filteredRestaurants.forEach { restaurant ->
//                    RestaurantCard(navController = navController, restaurant = restaurant)
//                }
//            }
//        }
//    }
//}
//
//
//fun searchRestaurants(input: String, restaurants: List<Restaurant>): List<Restaurant> {
//    return restaurants.filter { restaurant ->
//        restaurant.name.startsWith(input, ignoreCase = true)
//               // || restaurant.cuisine.name.contains(input, ignoreCase = true)
//    }
//}