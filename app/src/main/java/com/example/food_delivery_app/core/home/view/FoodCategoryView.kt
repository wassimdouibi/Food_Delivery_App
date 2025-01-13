package com.example.food_delivery_app.core.home.view

import androidx.compose.foundation.layout.*
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
import com.example.food_delivery_app.core.home.model.services.request.FoodFilterRequest
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
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                items(foodsFromSpecificCategory.size) { index ->
                    foodsFromSpecificCategory[index]?.let { it1 ->
//                        FoodMenuCard(
//                            navController = navController,
//                            foodResponse = it1,
//                            restaurantResponse = ,
//                        )
                    }
                }
            }
        }
    }
}