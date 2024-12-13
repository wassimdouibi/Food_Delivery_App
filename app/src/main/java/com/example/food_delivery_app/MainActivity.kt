package com.example.food_delivery_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.components.*
import com.example.food_delivery_app.ui.theme.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Food_Delivery_AppTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(
                                horizontal = 48.dp
                            )
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Greeting(
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}




@Composable
fun Greeting(modifier: Modifier = Modifier) {
    SegmentedButton(modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Food_Delivery_AppTheme {
        Greeting()
    }
}


//    Column {
//        SquareIconButton(
//            onClick = {},
//            modifier = modifier,
//            icon = Icons.Filled.Add,
//            iconDescription = "Add",
//            buttonSize = ButtonSize.LARGE
//        )
//        FilledTextButton(
//            onClick = {},
//            modifier = modifier,
//            textContent = "Log in",
//            buttonSize = ButtonSize.LARGE,
//            isPLargeBold = true
//        )
//        FilledTextButton(
//            onClick = {},
//            modifier = modifier,
//            textContent = "Log in",
//            buttonSize = ButtonSize.LARGE,
//            isPLargeBold = true,
//            enabled = false
//        )
//        GhostTextButton(
//            onClick = {},
//            modifier = modifier,
//            textContent = "Log in",
//            buttonSize = ButtonSize.LARGE,
//            isPLargeBold = true
//        )
//        BorderlessTextButton(
//            onClick = {},
//            modifier = modifier,
//            textContent = "Log in",
//            buttonSize = ButtonSize.LARGE,
//            isPLargeBold = true,
//        )
//        BorderlessTextButton(
//            onClick = {},
//            modifier = modifier,
//            textContent = "Log in",
//            buttonSize = ButtonSize.LARGE,
//            isPLargeBold = true,
//            enabled = false
//        )
//        RoundedIconBtn(
//            onClick = {},
//            modifier = modifier,
//            icon = Icons.Filled.Delete,
//            iconDescription = "Delete",
//            contentColor = LocalCustomColorScheme.current.ink50,
//            containerColor = LocalCustomColorScheme.current.utilityError
//        )
//        RoundedIconBtn(
//            onClick = {},
//            modifier = modifier,
//            icon = Icons.Filled.Delete,
//            iconDescription = "Delete",
//            contentColor = LocalCustomColorScheme.current.ink50,
//            containerColor = LocalCustomColorScheme.current.utilityError,
//            enabled = false
//        )
//    }