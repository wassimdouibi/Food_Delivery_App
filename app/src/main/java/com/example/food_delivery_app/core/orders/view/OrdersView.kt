package com.example.food_delivery_app.core.orders.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.core.components.CustomControl
import com.example.food_delivery_app.core.orders.model.entity.Order
import com.example.food_delivery_app.core.orders.view.components.OrderConfirmed
import com.example.food_delivery_app.core.orders.view.components.OrderDelivered
import com.example.food_delivery_app.core.orders.viewModel.OrdersViewModel
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme

val orders = listOf(
    Order(orderLoc = "123 Main St", deliveryLoc = "456 Elm St", price = 12.5f),
    Order(orderLoc = "789 Oak St", deliveryLoc = "321 Pine St", price = 15.0f),
    Order(orderLoc = "100 Maple Ave", deliveryLoc = "200 Cedar Ave", price = 20.75f),
    Order(orderLoc = "300 Birch Blvd", deliveryLoc = "400 Spruce Blvd", price = 18.3f),
    Order(orderLoc = "500 Walnut Dr", deliveryLoc = "600 Chestnut Dr", price = 22.0f)
)

@Composable
fun OrdersView(
    navController: NavHostController,
    ordersViewModel: OrdersViewModel,
) {
    var selectedIndex by remember { mutableStateOf(0) }
    val confirmedOrders: List<Order> = orders  // Pass confirmed orders
    val deliveredOrders: List<Order> = orders  // Pass delivered orders



    val isLoading by ordersViewModel.isLoading.collectAsState(initial = false)
    val error by ordersViewModel.error.collectAsState(initial = null)



    LaunchedEffect(Unit) {

    }

    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            CircularProgressIndicator()
        }
    } else if (true) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                error?.let {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = it,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }

            item {
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
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            if (selectedIndex == 0) {
                items(confirmedOrders) { order -> // Dynamic confirmed orders
                    OrderConfirmed(
                        orderLoc = order.orderLoc,
                        deliveryLoc = order.deliveryLoc,
                        price = order.price,
                        onButton1Click = {},
                        onButton2Click = {},
                        cardColor = Color.White
                    )
                }
            } else {
                items(deliveredOrders) { order -> // Dynamic delivered orders
                    OrderDelivered(
                        orderLoc = order.orderLoc,
                        deliveryLoc = order.deliveryLoc,
                        price = order.price,
                        cardColor = Color.White
                    )
                }
            }
        }
    } else {
        EmptyOrdersView(
            navController = navController,
            selectedIndex = selectedIndex,
            onChangeSelected = {
                selectedIndex = it
            }
        )
    }
}