package com.example.food_delivery_app.auth.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.food_delivery_app.auth.domain.AuthViewModel
import com.example.food_delivery_app.R
import com.example.food_delivery_app.components.ButtonIcon
import com.example.food_delivery_app.components.GhostTextButton
import com.example.food_delivery_app.components.IconType
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme


@Composable
fun OAuthSection(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val context = LocalContext.current
    GhostTextButton(
        onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                authViewModel.signInWthGoogle(context)
            }
        },

        textContent = stringResource(R.string.cta_login_with_google),
        icon = ButtonIcon.Left(
            IconType.PainterIcon(
                painter = painterResource(R.drawable.google),
                iconDescription = "Google icon",
            )
        ),

        modifier = Modifier.fillMaxWidth(),
        textStyle = LocalCustomTypographyScheme.current.p_mediumBold,
        contentColor = LocalCustomColorScheme.current.ink500,
        borderColor = LocalCustomColorScheme.current.ink100
    )
    if (authViewModel.authStatus) {
        LaunchedEffect(Unit) {
            // Navigation
        }
    }
}
