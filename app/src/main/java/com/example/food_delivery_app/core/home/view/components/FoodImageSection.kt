package com.example.food_delivery_app.core.home.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun FoodCardImageSection(pictures: List<String>) {
    // Image Section
    Row(
        modifier = Modifier.fillMaxWidth().height(400.dp)
    ) {
        // Left: Main Image
        Box(modifier = Modifier.weight(1f)) {
            AsyncImage(
                model = pictures[0], // Load image from URL
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(2.dp))

        // Right: Two Small Images
        Column(modifier = Modifier.weight(1f)) {
            AsyncImage(
                model = pictures[1], // Load image from URL
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(2.dp))
            AsyncImage(
                model = pictures[2], // Load image from URL
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentScale = ContentScale.Crop
            )
        }
    }
}