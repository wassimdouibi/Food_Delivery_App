package com.example.food_delivery_app.core.orders.view

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.example.food_delivery_app.core.orders.viewModel.OrdersViewModel


@Composable
fun OrdersView(
    navController: NavHostController,
    ordersViewModel: OrdersViewModel,
//    confirmedOrders: List<Order>,  // Pass confirmed orders
//    deliveredOrders: List<Order>   // Pass delivered orders
) {
//    var selectedIndex by remember { mutableStateOf(0) }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .systemBarsPadding()
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(.85f)
//                .padding(horizontal = 24.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//            CustomControl(
//                options = listOf(
//                    stringResource(R.string.order_ongoing_title),
//                    stringResource(R.string.order_history_title)
//                ),
//                selectedIndex = selectedIndex,
//                onOptionSelected = {
//                    selectedIndex = it
//                }
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            if (selectedIndex == 0) {
//                LazyColumn(
//                    modifier = Modifier.fillMaxSize(),
//                    verticalArrangement = Arrangement.spacedBy(16.dp)
//                ) {
//                    items(confirmedOrders) { order ->  // Display dynamic confirmed orders
//                        OrderConfirmed(
//                            orderLoc = order.orderLoc,
//                            deliveryLoc = order.deliveryLoc,
//                            price = order.price,
//                            onButton1Click = {},
//                            onButton2Click = {},
//                            cardColor = LocalCustomColorScheme.current.utilitySuccess
//                        )
//                    }
//                }
//            } else {
//                LazyColumn(
//                    modifier = Modifier.fillMaxSize(),
//                    verticalArrangement = Arrangement.spacedBy(16.dp)
//                ) {
//                    items(deliveredOrders) { order ->  // Display dynamic delivered orders
//                        OrderDelivered(
//                            orderLoc = order.orderLoc,
//                            deliveryLoc = order.deliveryLoc,
//                            price = order.price,
//                            cardColor = LocalCustomColorScheme.current.utilitySuccess
//                        )
//                    }
//                }
//            }
//        }
//    }
}