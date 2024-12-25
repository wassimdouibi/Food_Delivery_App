package com.example.food_delivery_app.auth.presentation.signup.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.components.BorderlessTextButton
import com.example.food_delivery_app.components.FilledTextButton
import com.example.food_delivery_app.navigation.Screen
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme

@Composable
fun SignupCard(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = LocalCustomColorScheme.current.ink50
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Fields
            Text("Email")
            Text("Password")

            FilledTextButton(
                onClick = {},
                textContent = stringResource(R.string.cta_signup_btn),
                textStyle = LocalCustomTypographyScheme.current.p_mediumBold,
                containerColor = LocalCustomColorScheme.current.utilityActive,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.user_registered),
                    color = LocalCustomColorScheme.current.ink400,
                    style = LocalCustomTypographyScheme.current.p_small
                )
                Spacer(modifier = Modifier.width(2.dp))
                BorderlessTextButton(
                    onClick = {
                        navController.navigate(Screen.Login.route)
                    },
                    textContent = stringResource(R.string.cta_login_msg),
                    textStyle = LocalCustomTypographyScheme.current.p_smallSemiBold,
                    contentColor = LocalCustomColorScheme.current.utilityActive,
                )
            }
        }
    }
}