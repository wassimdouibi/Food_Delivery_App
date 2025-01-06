package com.example.food_delivery_app

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import com.example.food_delivery_app.ui.theme.*
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.food_delivery_app.auth.domain.AuthViewModel
import com.example.food_delivery_app.auth.presentation.components.ResetPasswordSuccessBox
import com.example.food_delivery_app.auth.presentation.login.view.Login
import com.example.food_delivery_app.navigation.Navigation

var userId = 1;

class MainActivity : ComponentActivity() {
    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT > -Build.VERSION_CODES.TIRAMISU) {
            val hasPermission = ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
            if (!hasPermission) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 0
                )
            }
        }
    }

    @RequiresApi(34)
    override fun onCreate(savedInstanceState: Bundle?) {
        requestNotificationPermission()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Food_Delivery_AppTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val context = LocalContext.current
                    val pref = context.getSharedPreferences("local", Context.MODE_PRIVATE)

                    Navigation(
                        authViewModel = viewModel(),
                        pref = pref
                    )
                }
            }
        }
    }
}




//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .systemBarsPadding()
//                            .padding(horizontal = 16.dp),
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ){
//                        OrderConfirmed(
//                            orderLoc = "Warehouse A",
//                            deliveryLoc = "Customer Address",
//                            price = 1234.56f,
//
//                            onButton1Click = {},
//                            onButton2Click = {},
//
//                            cardColor = LocalCustomColorScheme.current.utilityWhiteBackground
//                        )
//
//                        OrderDelivered(
//                            orderLoc = "Warehouse A",
//                            deliveryLoc = "Customer Address",
//                            price = 1234.56f,
//
//                            cardColor = LocalCustomColorScheme.current.utilityWhiteBackground
//                        )
//
//                        OrderNotConfirmed(
//                            price = 1234.56f,
//                            onButton1Click = {},
//                            onButton2Click = {}
//                        )
//                        EditItem(
//                            text= "Add an item",
//                            onClick={},
//                            cardColor = LocalCustomColorScheme.current.primary100
//                        )
//                        ShopItem(
//                           price=98.2f,
//                           onClick = {},
//                            cardColor = LocalCustomColorScheme.current.primary400
//                        )
//                        AddToCardOrder(
//                            price=48f,
//                            initialValue = 0 ,
//                            onBtnTextLessClick={}
//                        )
//                        ItemCard(
//                            itemName = "Burger",
//                            price = 500f,
//                            imageRes = R.drawable.pizza, // Image dans le répertoire drawable
//                            noteTitle = "Note ",
//                            noteContent = "le note xxxxxx", // Ou une chaîne de caractères non vide pour tester
//                            onDeleteClick = { /* Handle item deletion */ },
//                            onBtnTextLessClick = { /* Handle TextLess button click */ },
//                            initialValue = 1, // Valeur initiale pour l'IncrementDecrementRow
//                            modifier = Modifier.padding(16.dp)
//                        )
//                        ItemCardEmptyNote(
//                            itemName = "Burger",
//                            price = 500f,
//                            imageRes = R.drawable.pizza, // Image dans le répertoire drawable
//                            onDeleteClick = { /* Handle item deletion */ },
//                            onBtnTextLessClick = { /* Handle TextLess button click */ },
//                            initialValue = 1, // Valeur initiale pour l'IncrementDecrementRow
//                            modifier = Modifier.padding(16.dp)
//                        )
//                    }