package com.example.food_delivery_app.core.orders.view

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.auth.model.entity.AuthPreferences
import com.example.food_delivery_app.core.Orders.view.OrderNotConfirmedView
import com.example.food_delivery_app.core.components.CustomControl
import com.example.food_delivery_app.core.orders.model.entity.Order
import com.example.food_delivery_app.core.orders.view.components.OrderConfirmed
import com.example.food_delivery_app.core.orders.view.components.OrderDelivered
import com.example.food_delivery_app.core.orders.viewModel.OrdersViewModel
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import kotlinx.coroutines.launch


@Composable
fun OrdersView(
    navController: NavHostController,
    ordersViewModel: OrdersViewModel,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val authPreferences = AuthPreferences(context)

    var selectedIndex by remember { mutableStateOf(1) }
    var userId by remember { mutableStateOf<String?>(null) }
    val ordersByUserId by ordersViewModel.ordersByUserId.collectAsState(emptyList())
    var selectedOrders by  remember { mutableStateOf<List<Order>>(emptyList()) }
    val selectedCart by ordersViewModel.selectedCart.collectAsState(null)

    val isLoading by ordersViewModel.isLoading.collectAsState(initial = false)
    val error by ordersViewModel.error.collectAsState(initial = null)
    var refreshTrigger by remember { mutableStateOf(false) }



    LaunchedEffect(Unit, userId) {
        authPreferences.userIdFlow.collect { userIdGot ->
            if (userIdGot != null) {  // Add null check
                userId = userIdGot
                ordersViewModel.getOrdersByUserId(userIdGot.toInt())
            }
        }
    }

    LaunchedEffect(ordersByUserId, selectedIndex) {
        selectedOrders = if (selectedIndex == 1) {
            ordersByUserId.filter { order ->
                order.status.lowercase() in setOf("pending", "on the way", "picked for delivery")
            }
        } else {
            ordersByUserId.filter { order ->
                order.status.lowercase() in setOf("delivered", "canceled")
            }
        }
    }


    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            CircularProgressIndicator()
        }
    } else if (ordersByUserId.isNotEmpty() || selectedCart != null) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                CustomControl(
                    options = listOf(
                        "New",
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
            when (selectedIndex) {
                0 -> {
//                    OrderNotConfirmedView(navController = navController, ordersViewModel = ordersViewModel, cart = selectedCart!!)
                }
                1 -> {
                    items(selectedOrders) { order -> // Dynamic confirmed orders
                        OrderConfirmed(
                            orderLoc = order.deliveryAddress,
                            deliveryLoc = order.deliveryAddress,
                            price = order.totalPrice.toFloat(),
                            onButton1Click = {
                                try {
                                    ordersViewModel.deleteOrder(order.orderId)
                                    // Provide success feedback
                                    Toast.makeText(context, "Order deleted successfully!", Toast.LENGTH_SHORT).show()
                                    refreshTrigger = true
                                } catch (e: Exception) {
                                    // Provide error feedback
                                    Toast.makeText(context, "Failed to delete order: ${e.message}", Toast.LENGTH_SHORT).show()
                                }
                            },
                            onButton2Click = {

                            },
                            cardColor = Color.White
                        )
                    }
                }
                else -> {
                    items(selectedOrders) { order -> // Dynamic delivered orders
                        OrderDelivered(
                            orderLoc = order.deliveryAddress,
                            deliveryLoc = order.deliveryAddress,
                            price = order.totalPrice.toFloat(),
                            cardColor = Color.White
                        )
                    }
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