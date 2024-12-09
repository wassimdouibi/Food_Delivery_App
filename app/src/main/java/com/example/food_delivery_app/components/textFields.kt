package com.example.food_delivery_app.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.food_delivery_app.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.ui.theme.defaultCustomColorScheme
import com.example.food_delivery_app.ui.theme.defaultCustomTypographyScheme

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "Password",
    modifier: Modifier = Modifier,
) {
    // State to toggle password visibility
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isFocuse by remember { mutableStateOf(false) }

    // State to track focus

    OutlinedTextField(

        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
            )
        },
        modifier = modifier
            .onFocusChanged { focusState -> isFocuse = focusState.isFocused }
            .border(
                width = 2.dp,
                color = if (isFocuse) {
                    defaultCustomColorScheme.primary400
                } else {
                    Color.Transparent
                },
                shape = RoundedCornerShape(10.dp)

            ),

        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Lock Icon"
            )
        },
        trailingIcon = {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = if (isPasswordVisible) "Hide Password" else "Show Password"
                )
            }
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),

        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

        textStyle = defaultCustomTypographyScheme.p_medium,

        )
}


@Composable
fun EmailInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "Email",
    modifier: Modifier = Modifier,
) {
    // State to track focus
    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder) },
        modifier = modifier
            .onFocusChanged { focusState -> isFocused = focusState.isFocused }
            .border(
                width = 2.dp,
                color = if (isFocused) defaultCustomColorScheme.primary400 else Color.Transparent,
                shape = RoundedCornerShape(10.dp)
            ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Mail,
                contentDescription = "mail icon"
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
        textStyle = defaultCustomTypographyScheme.p_medium,

    )
}


@Composable
fun PhoneNumberInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "Phone Number",
    modifier: Modifier = Modifier,
    countryCode: String = "+1" // Default country code for example
) {
    // State to track focus
    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder) },
        modifier = modifier
            .onFocusChanged { focusState -> isFocused = focusState.isFocused }
            .border(
                width = 2.dp,
                color = if (isFocused) defaultCustomColorScheme.primary400 else Color.Transparent,
                shape = RoundedCornerShape(10.dp)
            ),
        singleLine = true,
        leadingIcon = {
            // Display the country code as a leading icon
            Text(text = countryCode)
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
        textStyle = defaultCustomTypographyScheme.p_medium,
    )
}
