package com.example.food_delivery_app.core.home.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_delivery_app.core.components.RestaurantCard
import com.example.food_delivery_app.core.home.model.entity.Restaurant
import com.example.food_delivery_app.core.home.model.services.request.RestaurantFilterRequest
import com.example.food_delivery_app.core.home.viewModel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuisineRestaurantsView(
    navController: NavController,
    homeViewModel: HomeViewModel,
    cuisineName: String
) {
    val restaurantsFromSpecificCuisineType by homeViewModel.restaurantsFromSpecificCuisineType.collectAsState(emptyList())

    val isLoading by homeViewModel.isLoading.collectAsState(initial = false)

    LaunchedEffect(Unit) {
        homeViewModel.getRestaurantsOfCuisineType(RestaurantFilterRequest(cuisineType = cuisineName))
    }

    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "$cuisineName Restaurants") },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                items(restaurantsFromSpecificCuisineType.size) { index ->
                    RestaurantCard(
                        restaurant = restaurantsFromSpecificCuisineType[index],
                        navController = navController
                    )
                }
            }
        }
    }
}