package com.example.food_delivery_app.favorites.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun FavoritesResult(
    selectedIndex: Int
) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        items(10) {
            // depending on the selected index
            // if selected index == 0 it means restaurants else means foods
            if(selectedIndex == 0)
                RestaurantCard()
            else
                FoodCard()
        }
    }
}

