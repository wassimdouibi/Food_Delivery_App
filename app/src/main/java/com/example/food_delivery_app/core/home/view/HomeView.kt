package com.example.food_delivery_app.core.Home

import com.example.food_delivery_app.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_delivery_app.core.components.*
import com.example.food_delivery_app.core.home.view.components.CuisineIcon
import com.example.food_delivery_app.core.home.view.components.RestaurantSection
import com.example.food_delivery_app.core.home.view.components.SectionTitle
import com.example.food_delivery_app.core.home.viewModel.HomeViewModel


@Composable
fun HomeView(
    navController: NavController,
    homeViewModel: HomeViewModel,
) {
    val foodCategories by homeViewModel.foodCategories.collectAsState(initial = emptyList())
    val cuisineTypes by homeViewModel.cuisineTypes.collectAsState(initial = emptyList())
    val restaurants by homeViewModel.restaurants.collectAsState(initial = emptyList())

    val isLoading by homeViewModel.isLoading.collectAsState(initial = false)
    val error by homeViewModel.error.collectAsState(initial = null)
    var search by remember { mutableStateOf("") }
    var showFilterState by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        homeViewModel.getAllRestaurants()
        homeViewModel.getCategories()
        homeViewModel.getCuisineTypes()
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Loading and Error Handling
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
        error?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error, modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        // Search Bar
        FoodDeliveryTextField(
            value = search,
            onValueChange = { search = it },
            placeHolderText = stringResource(R.string.input_search_restaurant),
            leadingIconVector = Icons.Default.Search,
            trailingIconId = R.drawable.ri_equalizer_fill,
            modifier = Modifier.fillMaxWidth(.9f).align(Alignment.CenterHorizontally),
            changeShowFilterState = { showFilterState = !showFilterState },
            keyboardActions = KeyboardActions(
                onSearch = {
                    navController.navigate("search_results/${search}")
                }
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Cuisines Section
        SectionTitle("Cuisines")
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(cuisineTypes.size) { index ->
                CuisineIcon(
                    cuisineType = cuisineTypes[index],
                    onClick = {
//                        navController.navigate("cuisine/${cuisineTypes[index].id}")
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Top Categories
        SectionTitle("Top Categories")
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(foodCategories.size) { index ->
                CuisineIcon(
                    cuisineType = foodCategories[index],
                    onClick = {
//                        navController.navigate("category/${foodCategories[index].id}")
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Restaurant Sections
        RestaurantSection("Top Restaurants", restaurants, navController)
        RestaurantSection("Near Restaurants", restaurants, navController)
    }
}