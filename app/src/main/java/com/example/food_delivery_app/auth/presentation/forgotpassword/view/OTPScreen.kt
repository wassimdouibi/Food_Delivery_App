package com.example.food_delivery_app.auth.presentation.forgotpassword.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.auth.presentation.components.BackUpBar
import com.example.food_delivery_app.components.*
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme
import kotlinx.coroutines.delay


@Composable
fun OTPScreen(navController: NavController) {
    var char1 by remember {
        mutableStateOf("")
    }

    var seconds by remember { mutableStateOf(59) }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (seconds > 0) {
            delay(1000)
            seconds -= 1
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        BackUpBar(
            title = stringResource(R.string.change_password_title),
            navController = navController
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "${stringResource(R.string.check_email_description)} +213 5-------3",
                style = LocalCustomTypographyScheme.current.p_large,
                color = LocalCustomColorScheme.current.ink500
            )
            Spacer(modifier = Modifier.height(50.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    FoodDeliveryTextField(
                        value = char1,
                        onValueChange = {
                            char1 = it
                        },
                        leadingIcon = null,
                    )
                }
                Box(
                    modifier = androidx.compose.ui.Modifier.weight(1f)
                ) {
                    FoodDeliveryTextField(
                        value = char1,
                        onValueChange = {
                            char1 = it
                        },
                    )
                }
                Box(
                    modifier = androidx.compose.ui.Modifier.weight(1f)
                ) {
                    FoodDeliveryTextField(
                        value = char1,
                        onValueChange = {
                            char1 = it
                        },
                    )
                }
                Box(
                    modifier = androidx.compose.ui.Modifier.weight(1f)
                ) {
                    FoodDeliveryTextField(
                        value = char1,
                        onValueChange = {
                            char1 = it
                        },
                    )
                }
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    FoodDeliveryTextField(
                        value = char1,
                        onValueChange = {
                            char1 = it
                        },
                        leadingIcon = null,
                    )
                }
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    FoodDeliveryTextField(
                        value = char1,
                        onValueChange = {
                            char1 = it
                        },
                        leadingIcon = null,
                    )
                }
            }
            Spacer(modifier = Modifier.height(25.dp))

            if (seconds > 0)
                Row {
                    Text(
                        text = "Resend code in",
                        style = LocalCustomTypographyScheme.current.p_medium,
                    )
                    Text(
                        text = " ${"%02d".format(seconds)} ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = LocalCustomColorScheme.current.primary500,
                        modifier = Modifier.width(25.dp)
                    )
                    Text(
                        text = "s",
                        style = LocalCustomTypographyScheme.current.p_medium
                    )
                }
            else BorderlessTextButton(
                textContent = stringResource(R.string.send_code_again),
                textStyle = LocalCustomTypographyScheme.current.p_mediumBold,
                contentColor = LocalCustomColorScheme.current.primary500,
                onClick = {}
            )
        }

        FilledTextButton(
            onClick = {

            },

            textContent = stringResource(R.string.cta_verify_code),
            textStyle = LocalCustomTypographyScheme.current.p_mediumBold,
            icon = ButtonIcon.Right(
                IconType.VectorIcon(
                    imageVector = Icons.Default.ArrowForward,
                    iconDescription = "Icon forward"
                )
            ),
            modifier = Modifier.fillMaxWidth(),
        )

    }
}