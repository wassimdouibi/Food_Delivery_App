package com.example.food_delivery_app.core

import com.example.food_delivery_app.R
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_delivery_app.core.components.FoodDeliveryTextField
import com.example.food_delivery_app.core.components.RestaurantCard
import com.example.food_delivery_app.core.home.model.services.response.RestaurantResponse
import com.example.food_delivery_app.core.home.view.components.SectionTitle
import com.example.food_delivery_app.core.home.viewModel.HomeViewModel
import com.example.food_delivery_app.ui.theme.Colors.defaultCustomColorScheme
import com.example.food_delivery_app.ui.theme.Typography.defaultCustomTypographyScheme


@Composable
fun HomeSearchResultView(
    navController: NavController,
    initialSearchInput: String,
    homeViewModel: HomeViewModel
) {
    //  State to manage the search input and dynamically filter results
    val restaurants by homeViewModel.restaurants.collectAsState(initial = emptyList())
    var searchInput by remember { mutableStateOf(initialSearchInput) }
    var filteredRestaurants by remember { mutableStateOf(searchRestaurants(searchInput, restaurants)) }
    val isLoading by homeViewModel.isLoading.collectAsState(initial = false)

    LaunchedEffect(Unit) {
        homeViewModel.getAllRestaurants()
        filteredRestaurants = searchRestaurants(searchInput, restaurants)
    }


    if(isLoading){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(
                    vertical = 16.dp,
                    horizontal = 24.dp
                )
        ) {
            // Search Bar
            FoodDeliveryTextField(
                value = searchInput,
                onValueChange = {
                    searchInput = it
                    filteredRestaurants = searchRestaurants(searchInput, restaurants)
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

            Spacer(modifier = Modifier.height(16.dp))

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
                        RestaurantCard(
                            navController = navController,
                            restaurant = restaurant
                        )
                    }
                }
            }
        }
    }
}


fun searchRestaurants(input: String, restaurants: List<RestaurantResponse>): List<RestaurantResponse> {
    return restaurants.filter { restaurant ->
        restaurant.restaurant.name.contains(input, ignoreCase = true)
    }
}