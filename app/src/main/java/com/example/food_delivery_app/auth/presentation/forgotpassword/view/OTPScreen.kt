package com.example.food_delivery_app.auth.presentation.forgotpassword.view

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.auth.domain.AuthViewModel
import com.example.food_delivery_app.auth.presentation.components.BackUpBar
import com.example.food_delivery_app.components.*
import com.example.food_delivery_app.navigation.Screen
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme
import com.example.food_delivery_app.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    email: String,
) {
    val focusRequesters = List(6) { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    var otpChars = remember { mutableStateOf(List(6) { "" }) }
    var seconds by remember { mutableStateOf(59) }


    val context: Context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val verificationState = authViewModel.verificationState.collectAsState()
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(verificationState.value) {
        when (val state = verificationState.value) {
            is Resource.Loading -> {
                isLoading = true
            }
            is Resource.Success -> {
                isLoading = false
                // Navigate to reset password screen
                navController.navigate(Screen.ResetPassword.getScreen(email))
            }
            is Resource.Error -> {
                isLoading = false
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
                // Optionally clear the OTP input
                otpChars.value = List(6) { "" }
            }
            else -> {
                isLoading = false
            }
        }
    }


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

            Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                otpChars.value.forEachIndexed { index, char ->
                    OutlinedTextField(
                        value = char,
                        onValueChange = {
                            if (it.length <= 1) {
                                otpChars.value = otpChars.value.toMutableList().also { list -> list[index] = it }
                                if (it.isNotEmpty() && index < 5) {
                                    focusRequesters[index + 1].requestFocus()
                                } else if (it.isNotEmpty() && index == 5) {
                                    keyboardController?.hide()
                                }
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .focusRequester(focusRequesters[index]),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = if (index == 5) ImeAction.Done else ImeAction.Next
                        ),
                        singleLine = true,
                        textStyle = LocalCustomTypographyScheme.current.p_medium.copy(color = LocalCustomColorScheme.current.ink500),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedTextColor = LocalCustomColorScheme.current.ink500,
                            containerColor = LocalCustomColorScheme.current.ink100,
                            focusedBorderColor = LocalCustomColorScheme.current.primary400
                        )
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
                onClick = {
                    coroutineScope.launch {
                        authViewModel.sendVerification(
                            email = email
                        )
                    }
                }
            )
        }

        FilledTextButton(
            onClick = {
                val otpString = otpChars.value.joinToString(separator = "")
                if (otpString.length == 6) {
                    coroutineScope.launch {
                        authViewModel.verifyCode(
                            emailOrPhoneNumber = email,
                            code = otpString
                        )
                    }
                } else {
                    Toast.makeText(context, "Please enter a complete verification code", Toast.LENGTH_SHORT).show()
                }
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