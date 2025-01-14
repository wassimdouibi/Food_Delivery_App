package com.example.food_delivery_app.favorites.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_delivery_app.core.components.FoodMenuCard
import com.example.food_delivery_app.core.components.RestaurantCard
import com.example.food_delivery_app.core.home.model.services.response.FoodResponse
import com.example.food_delivery_app.core.home.model.services.response.RestaurantResponse


@Composable
fun FavoritesResult(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    favoriteFoods: List<FoodResponse> = emptyList(),
    favoriteRestaurants: List<RestaurantResponse> = emptyList(),
    navController: NavController
) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (selectedIndex == 0) {
            items(favoriteRestaurants) { restaurant ->
                RestaurantCard(
                    navController = navController,
                    restaurant = restaurant
                )
            }
        } else {
            items(favoriteFoods) { food ->
                favoriteRestaurants.firstOrNull()?.let {
                    FoodMenuCard(
                        navController = navController,
                        foodResponse = food,
                        restaurantResponse = it // Ensure this is not null
                    )
                }
            }
        }
    }
}
