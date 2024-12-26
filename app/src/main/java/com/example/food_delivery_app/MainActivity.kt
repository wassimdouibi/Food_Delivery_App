package com.example.food_delivery_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.components.EmailInput
import com.example.food_delivery_app.components.PasswordTextField
import com.example.food_delivery_app.components.PhoneNumberInput
import com.example.food_delivery_app.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Food_Delivery_AppTheme {
                InputsPreview()
            }
        }
    }
}

@Preview
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