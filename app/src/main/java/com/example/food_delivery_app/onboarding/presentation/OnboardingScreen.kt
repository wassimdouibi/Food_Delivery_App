package com.example.food_delivery_app.onboarding.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.R
import com.example.food_delivery_app.components.*
import com.example.food_delivery_app.onboarding.data.OnboardingItem
import com.example.food_delivery_app.onboarding.presentation.components.IndicatorsBox
import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.food_delivery_app.navigation.Screen
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme

@Composable
fun Onboarding(navController: NavController) {
    val onboardingItems: List<OnboardingItem> = OnboardingItem.get();
    var selectedItem = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            Image(
                painter = painterResource(id = onboardingItems[selectedItem.value].image),
                contentDescription = stringResource(onboardingItems[selectedItem.value].title),
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.weight(2f))

            Text(
                text = stringResource(onboardingItems[selectedItem.value].title),
                style = LocalCustomTypographyScheme.current.heading3.copy(
                    color = LocalCustomColorScheme.current.primary500
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(onboardingItems[selectedItem.value].description),
                style = LocalCustomTypographyScheme.current.p_medium.copy(
                    color = LocalCustomColorScheme.current.ink400
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            IndicatorsBox(
                nbIndicators = onboardingItems.size,
                selectedIndicator = selectedItem.value
            )

            Spacer(modifier = Modifier.weight(2f))

            if (selectedItem.value == onboardingItems.size - 1) {
                FilledTextButton(
                    onClick = {
                        navController.navigate(Screen.Signup.route)
                    },
                    textContent = stringResource(R.string.cta_get_started),
                    textStyle = LocalCustomTypographyScheme.current.p_largeBold,
                    icon = ButtonIcon.Right(
                        IconType.VectorIcon(
                            imageVector = Icons.Filled.ArrowForward,
                            iconDescription = "Arrow Forward"
                        )
                    ),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp)
                )
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    BorderlessTextButton(
                        onClick = {
                            if (selectedItem.value > 0)
                                selectedItem.value -= 1
                            else
                                selectedItem.value = 0
                        },
                        textContent = stringResource(R.string.Onboarding_cta_back),
                        textStyle = LocalCustomTypographyScheme.current.p_mediumBold
                    )
                    FilledTextButton(
                        onClick = { selectedItem.value += 1 },
                        textContent = stringResource(R.string.Onboarding_cta_next),
                        textStyle = LocalCustomTypographyScheme.current.p_mediumBold
                    )
                }
            }

            Spacer(modifier = Modifier.weight(2f))
    }
}