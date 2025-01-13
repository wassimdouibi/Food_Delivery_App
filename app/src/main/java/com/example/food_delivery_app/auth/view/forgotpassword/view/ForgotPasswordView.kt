package com.example.food_delivery_app.auth.view.forgotpassword.view

import android.content.SharedPreferences
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.auth.view.components.BackUpBar
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import com.example.food_delivery_app.auth.viewModel.AuthViewModel
import com.example.food_delivery_app.core.components.ButtonIcon
import com.example.food_delivery_app.core.components.EmailInput
import com.example.food_delivery_app.core.components.FilledTextButton
import com.example.food_delivery_app.core.components.IconType
import com.example.food_delivery_app.router.Router
import com.example.food_delivery_app.utils.Resource

@Composable
fun ForgotPasswordView(
    navController: NavController,
    authViewModel: AuthViewModel,
    pref: SharedPreferences
) {
    var email by remember { mutableStateOf("") }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope() // Get the coroutine scope

    val verificationState = authViewModel.sendVerificationState.collectAsState()

    // Handle verification state
    LaunchedEffect(verificationState.value) {
        when (val state = verificationState.value) {
            is Resource.Success -> {
                // Navigate to OTP screen with the email
                navController.navigate(Router.OTPScreen.createRoute(email = email))
            }
            is Resource.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            else -> {} // Handle other states if needed
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(24.dp),
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
                .height(200.dp)
                .padding(20.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = stringResource(R.string.contact_type),
            style = LocalCustomTypographyScheme.current.p_large,
        )

        var selectedWay by remember { mutableStateOf("Email") }

        Column {
            // Email Option
            ContactOption(
                title = stringResource(R.string.via_email),
                subtitle = "Enter your email to send the verification code",
                iconRes = R.drawable.message_bold,
                isSelected = selectedWay == "Email",
                onClick = { selectedWay = "Email" }
            )
            Spacer(modifier =  Modifier.height(8.dp))
            // Email or phone number Input
            EmailInput(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = stringResource(R.string.input_email)
            )
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
                if (email.isEmpty()) {
                    Toast.makeText(
                        context,
                        "Email is required",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@FilledTextButton
                }

                coroutineScope.launch {
                    authViewModel.sendVerification(
                        email = email
                    )
                }
            },
        )
    }
}


@Composable
fun ContactOption(
    title: String,
    subtitle: String? = null,
    iconRes: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) LocalCustomColorScheme.current.primary400 else LocalCustomColorScheme.current.ink100,
                shape = RoundedCornerShape(size = 8.dp)
            )
            .clip(RoundedCornerShape(size = 8.dp))
            .clickable { onClick() }
            .padding(15.dp)
    ) {
        Box(
            modifier = Modifier
                .size(75.dp)
                .clip(CircleShape)
                .background(LocalCustomColorScheme.current.primary100)
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = title,
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
            Text(text = title, style = LocalCustomTypographyScheme.current.p_medium)
            if(subtitle != null)
                Text(text = subtitle, style = LocalCustomTypographyScheme.current.p_mediumBold)
        }
    }
}