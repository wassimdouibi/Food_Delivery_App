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
import com.example.food_delivery_app.core.home.view.components.SectionTitle
import com.example.food_delivery_app.core.home.viewModel.HomeViewModel
import com.example.food_delivery_app.router.Router


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

    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
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
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
                changeShowFilterState = { showFilterState = !showFilterState },
                keyboardActions = KeyboardActions(
                    onSearch = {
                        navController.navigate(Router.HomeSearchResultScreen.createRoute(search))
                    }
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Cuisines Section
            SectionTitle("Cuisines")
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(cuisineTypes.size) { index ->
                    CuisineIcon( cuisineType = cuisineTypes[index] , navController = navController)
                }
            }


            Spacer(modifier = Modifier.height(24.dp))


            // Top Categories
            SectionTitle("Top Categories")
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(foodCategories.size) { index ->
                    CuisineIcon(cuisineType = foodCategories[index], navController = navController)
                }
            }


            Spacer(modifier = Modifier.height(24.dp))

            // Restaurant Sections
            SectionTitle(title = "Top Restaurants")
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(restaurants.size) { index ->
                    RestaurantCard( restaurant = restaurants[index], navController = navController)
                }
            }


            Spacer(modifier = Modifier.height(24.dp))


            SectionTitle(title = "Near Restaurants")
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(restaurants.size) { index ->
                    RestaurantCard( restaurant = restaurants[index], navController = navController )
                }
            }
        }
    }
}