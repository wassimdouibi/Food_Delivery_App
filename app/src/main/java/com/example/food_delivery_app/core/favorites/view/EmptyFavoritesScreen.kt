package com.example.food_delivery_app.core.favorites.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.core.components.CustomControl
import com.example.food_delivery_app.core.components.FilledTextButton
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme

@Composable
fun EmptyFavoritesScreen() {
    var selectedIndex by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.75f)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CustomControl(
                options = listOf(
                    stringResource(R.string.restaurants),
                    stringResource(R.string.foods)
                ),
                selectedIndex = selectedIndex,
                onOptionSelected = {
                    selectedIndex = it
                }
            )

            Spacer(modifier = Modifier.weight(3f))

            Image(
                painter = painterResource(R.drawable.wavy_buddies_preparing_your_food_without_bg),
                contentDescription = "Empty Body",
                modifier = Modifier
                    .height(220.dp)
                    .padding(20.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.FillHeight

            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(R.string.favorites_empty_title),
                style = LocalCustomTypographyScheme.current.heading3,
                color = LocalCustomColorScheme.current.ink500,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text =
                    if(selectedIndex == 0)
                        stringResource(R.string.favorites_empty_restaurants_subtitle)
                    else
                        stringResource(R.string.favorites_empty_foods_subtitle),
                style = LocalCustomTypographyScheme.current.p_medium,
                color = LocalCustomColorScheme.current.ink400,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

            FilledTextButton(
                onClick = {
                    // Navigate to home

                },
                textContent = stringResource(R.string.cta_back_home),
                textStyle = LocalCustomTypographyScheme.current.p_largeBold
            )
        }
    }
}