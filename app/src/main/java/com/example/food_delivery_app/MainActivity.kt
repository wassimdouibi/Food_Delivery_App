package com.example.food_delivery_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import com.example.food_delivery_app.ui.theme.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.components.*
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
                    Navigation()
//                    Signup(navController = rememberNavController(),)
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
                }
            }
        }
    }
}