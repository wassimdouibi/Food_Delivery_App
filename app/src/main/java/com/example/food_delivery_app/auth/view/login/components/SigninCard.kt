package com.example.food_delivery_app.auth.view.login.components

import android.content.SharedPreferences
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.auth.model.entity.AuthState
import com.example.food_delivery_app.auth.viewModel.AuthViewModel
import com.example.food_delivery_app.auth.view.components.CheckBoxBtn
import com.example.food_delivery_app.auth.view.components.OAuthSection
import com.example.food_delivery_app.core.components.BorderlessTextButton
import com.example.food_delivery_app.core.components.EmailInput
import com.example.food_delivery_app.core.components.FilledTextButton
import com.example.food_delivery_app.core.components.PasswordTextField
import com.example.food_delivery_app.router.Router
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SigninCard(
    navController: NavController,
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel,
    pref: SharedPreferences
) {
    // State variables for input values
    var emailorPhoneValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }

    val context = LocalContext.current
    val authState by authViewModel.authState.collectAsState()

    // Handle auth state changes
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Success -> {
                navController.navigate(Router.FoodDeliveryNavScreen.route)
            }
            else -> {}
        }
    }



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
                authViewModel = authViewModel,
                pref = pref
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


            // Email or phone number Input
            EmailInput(
                value = emailorPhoneValue,
                onValueChange = { emailorPhoneValue = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = stringResource(R.string.input_email_or_phone_number)
            )

            // Password Input
            PasswordTextField(
                value = passwordValue,
                onValueChange = { passwordValue = it },
                modifier = Modifier.fillMaxWidth()
            )

            FilledTextButton(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        authViewModel.login(
                            emailorPhoneValue,
                            passwordValue,
                            pref
                        )
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
                        navController.navigate(Router.ForgotPasswordScreen.route)
                    }
                )

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.user_not_registered),
                    color = LocalCustomColorScheme.current.ink400,
                    style = LocalCustomTypographyScheme.current.p_small
                )
                BorderlessTextButton(
                    onClick = {
                        navController.navigate(Router.SignupScreen.route)
                    },
                    textContent = stringResource(R.string.cta_signup_btn),
                    textStyle = LocalCustomTypographyScheme.current.p_smallSemiBold,
                    contentColor = LocalCustomColorScheme.current.utilityActive,
                )
            }
        }
    }
}