package com.example.food_delivery_app.auth.presentation.signup.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.auth.domain.AuthViewModel
import com.example.food_delivery_app.auth.presentation.components.BackgroundScreen
import com.example.food_delivery_app.auth.presentation.signup.components.SignupCard
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme

@Composable
fun Signup(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        BackgroundScreen()

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.weight(.5f))

            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.scale(2f)
            )

            Spacer(modifier = Modifier.weight(.25f))

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.signup_title),
                    style = LocalCustomTypographyScheme.current.heading2.copy(
                        fontWeight = FontWeight.Bold,
                        color = LocalCustomColorScheme.current.ink50
                    )
                )
                Text(
                    text = stringResource(R.string.signup_description),
                    style = LocalCustomTypographyScheme.current.p_medium.copy(
                        color = LocalCustomColorScheme.current.ink100
                    )
                )

            }

            Spacer(modifier = Modifier.weight(.5f))

            SignupCard(
                navController = navController,
                modifier = Modifier
                    .weight(2f)
                    .fillMaxWidth(.75f)
            )

            Spacer(modifier = Modifier.weight(1.25f))
        }
    }
}