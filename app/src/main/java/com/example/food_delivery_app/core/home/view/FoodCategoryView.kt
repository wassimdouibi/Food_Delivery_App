package com.example.food_delivery_app.core.home.view

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_delivery_app.core.components.FoodMenuCard
import com.example.food_delivery_app.core.home.model.entity.CuisineType
import com.example.food_delivery_app.core.home.model.entity.Restaurant
import com.example.food_delivery_app.core.home.model.services.request.FoodFilterRequest
import com.example.food_delivery_app.core.home.model.services.response.RestaurantResponse
import com.example.food_delivery_app.core.home.viewModel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodCategoryView(
    navController: NavController,
    homeViewModel: HomeViewModel,
    foodCategoryName: String
) {
    val foodsFromSpecificCategory by homeViewModel.foodsFromSpecificCategory.collectAsState(emptyList())

    val isLoading by homeViewModel.isLoading.collectAsState(initial = false)

    LaunchedEffect(Unit) {
        homeViewModel.getFoodsFromSpecificCategory(FoodFilterRequest(category = foodCategoryName))
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
                    title = { Text(text = "$foodCategoryName Category") },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 32.dp)
            ) {
                items(foodsFromSpecificCategory.size) { index ->
                    foodsFromSpecificCategory[index]?.let { it1 ->
                        FoodMenuCard(
                            navController = navController,
                            foodResponse = it1,
                            restaurantResponse = RestaurantResponse(
                                Restaurant(
                                    restaurantId = 4,
                                    name = "Gourmet Paradise",
                                    logo = "",
                                    address = "456 Ocean Avenue, Fish City",
                                    cuisineType = 19,
                                    averageRating = 4.toFloat(),
                                    reviewCount = 423,
                                    contactPhone = "0543684916",
                                    contactEmail = "GourmetParadise@gmail.com",
                                    fbLink = "https://facebookLink.com",
                                    instaLink = "https://instagramLink.com"
                                ),
                                CuisineType(
                                    cuisineTypeId = 19,
                                    name = "Italian",
                                    image = "italian_cuisine.jpg"
                                ),
                                tags = emptyList(),
                                pics = emptyList()
                            ),
                        )
                    }
                }
            }
        }
    }
}