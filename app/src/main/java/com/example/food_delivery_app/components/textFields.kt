package com.example.food_delivery_app.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.food_delivery_app.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme
import com.example.food_delivery_app.ui.theme.defaultCustomColorScheme
import com.example.food_delivery_app.ui.theme.defaultCustomTypographyScheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = stringResource(R.string.input_password),
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    supportingText: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    shape: Shape = RoundedCornerShape(size = 8.dp),
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
                style = LocalCustomTypographyScheme.current.p_medium,
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = LocalCustomColorScheme.current.primary400 ,
            unfocusedIndicatorColor = Color(0xFFACB5BB),
            disabledIndicatorColor = Color(0xFFACB5BB),
            errorIndicatorColor = LocalCustomColorScheme.current.utilityError,
        ),
        modifier = modifier
            .clip(shape)
            .onFocusChanged { focusState -> isFocuse = focusState.isFocused },

        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "Lock Icon",
                modifier = Modifier.size(20.dp),
                tint = Color(0xFFACB5BB)
            )
        },
        trailingIcon = {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = if (isPasswordVisible) "Hide Password" else "Show Password",
                    modifier = Modifier.size(20.dp),
                    tint = Color(0xFFACB5BB)
                )
            }
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),

        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,

        textStyle = LocalCustomTypographyScheme.current.p_medium,

        enabled = enabled,
        readOnly = readOnly,
        supportingText = supportingText,
        shape = shape
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = stringResource(R.string.input_email),
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    supportingText: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    shape: Shape = RoundedCornerShape(size = 8.dp),

    ) {
    // State to track focus
    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                style = LocalCustomTypographyScheme.current.p_medium,
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = LocalCustomColorScheme.current.primary400 ,
            unfocusedIndicatorColor = Color(0xFFACB5BB),
            disabledIndicatorColor = Color(0xFFACB5BB),
            errorIndicatorColor = LocalCustomColorScheme.current.utilityError
        ),
        modifier = modifier
            .clip(shape)
            .onFocusChanged { focusState -> isFocused = focusState.isFocused },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Mail,
                contentDescription = "mail icon",
                modifier = Modifier.size(20.dp),
                tint = Color(0xFFACB5BB)
            )
        },
        singleLine = true,

        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,

        textStyle = LocalCustomTypographyScheme.current.p_medium,

        enabled = enabled,
        readOnly = readOnly,
        supportingText = supportingText,
        shape = shape

    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneNumberInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = stringResource(R.string.input_phone_number),
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    supportingText: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    shape: Shape = RoundedCornerShape(size = 8.dp),
) {
    // State to track focus
    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                style = LocalCustomTypographyScheme.current.p_medium,
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = LocalCustomColorScheme.current.primary400 ,
            unfocusedIndicatorColor = Color(0xFFACB5BB),
            disabledIndicatorColor = Color(0xFFACB5BB),
            errorIndicatorColor = LocalCustomColorScheme.current.utilityError
        ),
        shape = shape,
        modifier = modifier
            .clip(shape)
            .onFocusChanged { focusState -> isFocused = focusState.isFocused },
        singleLine = true,
        leadingIcon = {
            // Display the country code as a leading icon
            Text(
                text = "+213",
                color =  Color(0xFFACB5BB)
            )
        },

        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,

        textStyle = LocalCustomTypographyScheme.current.p_medium,

        enabled = enabled,
        readOnly = readOnly,
        supportingText = supportingText
    )
}



//function to display the 3 inputs incase someone wants to visualise them to test
@Composable
fun InputsPreview() {
    // State variables for input values
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val phoneNumberValue = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Email Input
        EmailInput(
            value = emailValue.value,
            onValueChange = { emailValue.value = it },
            placeholder = "Enter your email"
        )

        // Space between inputs
        Spacer(modifier = Modifier.height(16.dp))

        // Password Input
        PasswordTextField(
            value = passwordValue.value,
            onValueChange = { passwordValue.value = it },
            placeholder = "Enter your password"
        )

        // Space between inputs
        Spacer(modifier = Modifier.height(16.dp))

        // Phone Number Input
        PhoneNumberInput(
            value = phoneNumberValue.value,
            onValueChange = { phoneNumberValue.value = it },
            placeholder = "Enter your phone number",
        )
    }
}
