package com.example.food_delivery_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import com.example.food_delivery_app.ui.theme.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.navigation.compose.rememberNavController
import com.example.food_delivery_app.auth.presentation.signup.Signup
import com.example.food_delivery_app.navigation.Navigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Food_Delivery_AppTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    innerPadding ->
//                    Navigation()
                    Signup(navController = rememberNavController(),)
                }
            }
        }
    }
}