package com.example.food_delivery_app.core.orders.view

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
import androidx.navigation.NavHostController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.core.components.CustomControl
import com.example.food_delivery_app.core.components.FilledTextButton
import com.example.food_delivery_app.router.Router
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme

@Composable
fun EmptyOrdersView(
    navController: NavHostController,
    selectedIndex: Int,
    onChangeSelected: (Int) -> Unit,
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
                stringResource(R.string.order_ongoing_title),
                stringResource(R.string.order_history_title)
            ),
            selectedIndex = selectedIndex,
            onOptionSelected = {
                    index -> onChangeSelected(index)
            }
        )

        Spacer(modifier = Modifier.weight(3f))

        Image(
            painter = painterResource(R.drawable.img_wavy_buddies_standing),
            contentDescription = "Standing Body",
            modifier = Modifier
                .height(220.dp)
                .padding(20.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.FillHeight

        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = stringResource(R.string.order_empty_title),//No requests
            style = LocalCustomTypographyScheme.current.heading3,
            color = LocalCustomColorScheme.current.ink500,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text =
            stringResource(R.string.order_empty_subtitle),//text history
            style = LocalCustomTypographyScheme.current.p_medium,
            color = LocalCustomColorScheme.current.ink400,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))

        FilledTextButton(
            onClick = {
                // Navigate to home
                navController.navigate(Router.HomeScreen.route)
            },
            textContent = stringResource(R.string.cta_back_home),
            textStyle = LocalCustomTypographyScheme.current.p_largeBold
        )
    }
}