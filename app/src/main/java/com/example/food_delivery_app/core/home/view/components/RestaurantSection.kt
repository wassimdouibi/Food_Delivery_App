package com.example.food_delivery_app.core.home.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_delivery_app.core.components.RestaurantCard
import com.example.food_delivery_app.core.home.model.services.response.RestaurantResponse



@Composable
fun RestaurantSection(
    title: String,
    restaurants: List<RestaurantResponse>,
    navController: NavController
) {
    SectionTitle(title = title)
    Spacer(modifier = Modifier.height(8.dp))
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(restaurants.size) { index ->
            RestaurantCard( navController = navController, restaurant = restaurants[index] )
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}
