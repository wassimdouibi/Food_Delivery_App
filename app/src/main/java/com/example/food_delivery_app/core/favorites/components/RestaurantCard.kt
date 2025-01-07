package com.example.food_delivery_app.favorites.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun RestaurantCard(

){
    Box(
        modifier = Modifier
            .size(120.dp)
            .border(1.dp, Color.Black),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Restaurant Card",
            textAlign = TextAlign.Center,
        )
    }

}