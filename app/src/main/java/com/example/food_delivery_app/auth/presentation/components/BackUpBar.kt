package com.example.food_delivery_app.auth.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme

@Composable
fun BackUpBar(
    title: String,
    navController: NavController
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow_left_outlined),
            contentDescription = "Go Back",
            modifier = Modifier
                .size(32.dp)
                .clickable {
                    navController.popBackStack()
                }
        )
        Text(
            text = title,
            style = LocalCustomTypographyScheme.current.heading3,
            color = LocalCustomColorScheme.current.ink500,
        )
    }
}