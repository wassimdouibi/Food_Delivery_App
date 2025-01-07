package com.example.food_delivery_app.auth.presentation.forgotpassword.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.auth.presentation.components.BackUpBar
import com.example.food_delivery_app.components.ButtonIcon
import com.example.food_delivery_app.components.FilledTextButton
import com.example.food_delivery_app.components.IconType
import com.example.food_delivery_app.navigation.Screen
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme


@Composable
fun ForgotPassword(
    navController: NavController
) {
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

        Image(
            painter = painterResource(id = R.drawable.security),
            contentDescription = "Recover Password",
            modifier = Modifier
                .height(250.dp)
                .padding(20.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = stringResource(R.string.contact_type),
            style = LocalCustomTypographyScheme.current.p_large,
        )

        var selectedWay by remember {
            mutableStateOf("Email")
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = if (selectedWay == "SMS") 2.dp else 1.dp,
                    color = if (selectedWay == "SMS") LocalCustomColorScheme.current.primary400 else LocalCustomColorScheme.current.ink100,
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .clip(RoundedCornerShape(size = 8.dp))
                .clickable {
                    selectedWay = "SMS"
                }
                .padding(15.dp)

        ) {
            Box(
                modifier = Modifier
                    .size(75.dp)
                    .clip(CircleShape)
                    .background(LocalCustomColorScheme.current.primary100)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.chat_bold),
                    contentDescription = "SMS",
                    colorFilter = ColorFilter.tint(
                        LocalCustomColorScheme.current.primary500
                    ),
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.width(15.dp))

            Column {
                Text(text = stringResource(R.string.via_sms), style = LocalCustomTypographyScheme.current.p_medium)
                Text(text = "+213 5 -------3", style = LocalCustomTypographyScheme.current.p_mediumBold)
            }

        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = if (selectedWay == "Email") 2.dp else 1.dp,
                    color = if (selectedWay == "Email") LocalCustomColorScheme.current.primary400 else LocalCustomColorScheme.current.ink100,
                    shape = RoundedCornerShape(size = 15.dp)
                )
                .clip(RoundedCornerShape(size = 15.dp))
                .clickable {
                    selectedWay = "Email"
                }
                .padding(15.dp)

        ) {
            Box(
                modifier = Modifier
                    .size(75.dp)
                    .clip(CircleShape)
                    .background(LocalCustomColorScheme.current.primary100),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.message_bold),
                    contentDescription = "Email",
                    colorFilter = ColorFilter.tint(
                        LocalCustomColorScheme.current.primary500
                    ),
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center)

                )
            }

            Spacer(modifier = Modifier.width(15.dp))

            Column {
                Text(text = stringResource(R.string.via_email), style = LocalCustomTypographyScheme.current.p_medium)
                Text(text = "lw---------@---.dz", style = LocalCustomTypographyScheme.current.p_mediumBold)
            }

        }

        FilledTextButton(
            textContent = stringResource(R.string.cta_send_code),
            textStyle = LocalCustomTypographyScheme.current.p_mediumBold,
            modifier = Modifier.fillMaxWidth(),
            icon = ButtonIcon.Right(
                IconType.VectorIcon(
                    imageVector = Icons.Default.ArrowForward,
                    iconDescription = "Forward Icon"
                )
            ),
            onClick = {
                navController.navigate(Screen.OTPScreen.route)
            }
        )

    }
}