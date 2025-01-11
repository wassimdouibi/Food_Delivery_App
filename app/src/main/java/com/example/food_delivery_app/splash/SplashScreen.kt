package com.example.food_delivery_app.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.food_delivery_app.R
import com.example.food_delivery_app.router.Router
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme

@Composable
fun Splash(
    navController: NavController,
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(LocalCustomColorScheme.current.primary700),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val composition = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.logo_animation))
        val logoAnimationState =
            animateLottieCompositionAsState(composition = composition.value)

        LottieAnimation(
            composition = composition.value,
            progress = { logoAnimationState.progress }
        )
        if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
            navController.navigate(Router.OnboardingScreen.route)
        }

        Text(
            text = stringResource(R.string.slogan),
            style = LocalCustomTypographyScheme.current.heading5
        )
    }
}