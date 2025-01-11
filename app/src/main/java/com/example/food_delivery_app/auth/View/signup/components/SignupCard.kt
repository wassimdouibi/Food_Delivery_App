package com.example.food_delivery_app.auth.View.signup.components

import android.widget.Toast
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
import com.example.food_delivery_app.auth.Model.entity.AuthState
import com.example.food_delivery_app.auth.ViewModel.AuthViewModel
import com.example.food_delivery_app.auth.View.components.OAuthSection
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
fun SignupCard(
    navController: NavController,
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel,
) {
    // State variables for input values
    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }

    val context = LocalContext.current
    val authState by authViewModel.authState.collectAsState()

    // Handle auth state changes
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Success -> {
                Toast.makeText(
                    context,
                    "Successfully signed up",
                    Toast.LENGTH_SHORT
                ).show()
                navController.navigate(Router.EditProfileScreen.route)
            }
            is AuthState.Error -> {
                Toast.makeText(
                    context,
                    (authState as AuthState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {} // Handle other states if needed
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
            // Email Input
            EmailInput(
                value = emailValue,
                onValueChange = { emailValue = it },
                modifier = Modifier.fillMaxWidth()
            )

            // Password Input
            PasswordTextField(
                value = passwordValue,
                onValueChange = { passwordValue = it },
                modifier = Modifier.fillMaxWidth()
            )

            FilledTextButton(
                onClick = {
                    if (emailValue.isNotEmpty() && passwordValue.isNotEmpty()) {
                        CoroutineScope(Dispatchers.IO).launch {
                            authViewModel.register(
                                email = emailValue,
                                password = passwordValue
                            )
                        }
                    } else {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    }
                },
                textContent = stringResource(R.string.cta_signup_btn),
                textStyle = LocalCustomTypographyScheme.current.p_mediumBold,
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
                        navController.navigate(Router.LoginScreen.route)
                    },
                    textContent = stringResource(R.string.cta_login_msg),
                    textStyle = LocalCustomTypographyScheme.current.p_smallSemiBold,
                    contentColor = LocalCustomColorScheme.current.utilityActive,
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = " or ",
                    style = LocalCustomTypographyScheme.current.p_small,
                    color = LocalCustomColorScheme.current.ink400
                )
                HorizontalDivider(
                    modifier = Modifier.weight(1f)
                )
            }

            OAuthSection(
                navController = navController,
                authViewModel = authViewModel
            )
        }
    }
}