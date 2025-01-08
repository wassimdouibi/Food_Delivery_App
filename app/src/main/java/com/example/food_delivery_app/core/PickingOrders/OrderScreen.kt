package com.example.food_delivery_app

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.food_delivery_app.components.*
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme


@Composable
fun OrderScreen(
    navController: NavHostController,
    confirmedOrders: List<Order>,  // Pass confirmed orders
    deliveredOrders: List<Order>   // Pass delivered orders
) {
    var selectedIndex by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.85f)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CustomControl(
                options = listOf(
                    stringResource(R.string.order_ongoing_title),
                    stringResource(R.string.order_history_title)
                ),
                selectedIndex = selectedIndex,
                onOptionSelected = {
                    selectedIndex = it
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (selectedIndex == 0) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(confirmedOrders) { order ->  // Display dynamic confirmed orders
                        OrderConfirmed(
                            orderLoc = order.orderLoc,
                            deliveryLoc = order.deliveryLoc,
                            price = order.price,
                            onButton1Click = {},
                            onButton2Click = {},
                            cardColor = LocalCustomColorScheme.current.utilitySuccess
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(deliveredOrders) { order ->  // Display dynamic delivered orders
                        OrderDelivered(
                            orderLoc = order.orderLoc,
                            deliveryLoc = order.deliveryLoc,
                            price = order.price,
                            cardColor = LocalCustomColorScheme.current.utilitySuccess
                        )
                    }
                }
            }
        }

        BottomBar(
            navController = navController,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}

//
//package com.example.food_delivery_app
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.example.food_delivery_app.ui.theme.Food_Delivery_AppTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge() // If required for edge-to-edge layout
//        setContent {
//            Food_Delivery_AppTheme {
//                val navController = rememberNavController() // Initialize the NavController
//
//                // Example data for confirmed and delivered orders
//                val confirmedOrders = listOf(
//                    Order("Location 1", "Delivery Location 1", 3500f),
//                    Order("Location 2", "Delivery Location 2", 2200f),
//                    Order("Location 3", "Delivery Location 3", 4000f)
//                )
//
//                val deliveredOrders = listOf(
//                    Order("Hydra algiers", "Oued semar", 4000f),
//                    Order("El Harrach", "Birkhadem", 2500f)
//                )
//
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    OrderScreen(
//                        navController = navController,
//                        confirmedOrders = confirmedOrders,
//                        deliveredOrders = deliveredOrders
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    Food_Delivery_AppTheme {
//        Greeting("Android")
//    }
//}



//
//data class Order(
//    val orderLoc: String,
//    val deliveryLoc: String,
//    val price: Float
//)