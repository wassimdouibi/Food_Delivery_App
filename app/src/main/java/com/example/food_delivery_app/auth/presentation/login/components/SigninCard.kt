package com.example.food_delivery_app.auth.presentation.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.auth.domain.AuthViewModel
import com.example.food_delivery_app.auth.presentation.components.CheckBoxBtn
import com.example.food_delivery_app.auth.presentation.components.OAuthSection
import com.example.food_delivery_app.components.BorderlessTextButton
import com.example.food_delivery_app.components.EmailInput
import com.example.food_delivery_app.components.FilledTextButton
import com.example.food_delivery_app.components.PasswordTextField
import com.example.food_delivery_app.navigation.Screen
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SigninCard(
    navController: NavController,
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel
) {
    // State variables for input values
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }

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
            OAuthSection(
                navController = navController,
                authViewModel = authViewModel
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = stringResource(R.string.login_with_other_methods),
                    style = LocalCustomTypographyScheme.current.p_small,
                    color = LocalCustomColorScheme.current.ink400
                )
                HorizontalDivider(
                    modifier = Modifier.weight(1f)
                )
            }


            // Email Input
            EmailInput(
                value = emailValue.value,
                onValueChange = { emailValue.value = it },
                modifier = Modifier.fillMaxWidth()
            )

            // Password Input
            PasswordTextField(
                value = passwordValue.value,
                onValueChange = { passwordValue.value = it },
                modifier = Modifier.fillMaxWidth()
            )

            FilledTextButton(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
//                        authViewModel.login()
                    }
                },
                textContent = stringResource(R.string.cta_login_btn),
                textStyle = LocalCustomTypographyScheme.current.p_mediumBold,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CheckBoxBtn(
                        value = true
//                        value = authViewModel.rememberMe,
                    ) {
//                        authViewModel.rememberMe = !authViewModel.rememberMe
                    }

                    Text(
                        text = stringResource(R.string.remember_me),
                        style = LocalCustomTypographyScheme.current.p_small,
                        color = LocalCustomColorScheme.current.ink400
                    )
                }

                BorderlessTextButton(
                    textContent = stringResource(R.string.forgot_password),
                    textStyle = LocalCustomTypographyScheme.current.p_smallSemiBold,
                    contentColor = LocalCustomColorScheme.current.utilityActive,
                    onClick = {
                        navController.navigate(Screen.ForgotPassword.route)
                    }
                )

            }

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