package com.example.food_delivery_app.auth.presentation.login.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.auth.domain.AuthViewModel
import com.example.food_delivery_app.auth.presentation.components.BackgroundScreen
import com.example.food_delivery_app.auth.presentation.login.components.SigninCard
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme

@Composable
fun Login(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            BackgroundScreen()

            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
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
                        text = stringResource(R.string.login_title),
                        style = LocalCustomTypographyScheme.current.heading2.copy(
                            fontWeight = FontWeight.Bold,
                            color = LocalCustomColorScheme.current.ink50
                        )
                    )
                    Text(
                        text = stringResource(R.string.login_description),
                        style = LocalCustomTypographyScheme.current.p_medium.copy(
                            color = LocalCustomColorScheme.current.ink100
                        )
                    )

                }

                Spacer(modifier = Modifier.weight(.5f))

                SigninCard(
                    authViewModel = authViewModel,
                    navController = navController,
                    modifier = Modifier
                        .weight(3f)
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.weight(1.25f))
            }
        }
    }
}