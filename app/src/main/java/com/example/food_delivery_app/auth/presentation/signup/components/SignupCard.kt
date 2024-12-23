package com.example.food_delivery_app.auth.presentation.signup.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.components.ButtonSize
import com.example.food_delivery_app.components.FilledTextButton
import com.example.food_delivery_app.navigation.Screen
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme

@Composable
fun SignupCard(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(LocalCustomColorScheme.current.utilityWhiteBackground),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FieldsCard()

        FilledTextButton(
            onClick = {},
            buttonSize = ButtonSize.LARGE,
            textContent = stringResource(R.string.cta_signup_btn),
            containerColor = LocalCustomColorScheme.current.utilityActive
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.user_registered),
                color = LocalCustomColorScheme.current.ink500,
                style = LocalCustomTypographyScheme.current.p_small
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = stringResource(R.string.cta_login_msg),
                color = LocalCustomColorScheme.current.utilityActive,
                style = LocalCustomTypographyScheme.current.p_smallSemiBold,
                modifier = Modifier
                    .clickable {
                    navController.navigate(Screen.Login.route)
                }
            )
        }
    }
}