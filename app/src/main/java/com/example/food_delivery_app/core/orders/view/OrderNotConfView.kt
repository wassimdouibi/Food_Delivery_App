package com.example.food_delivery_app.core.Orders.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.food_delivery_app.core.components.Item
import com.example.food_delivery_app.core.orders.model.entity.Cart
import com.example.food_delivery_app.core.orders.model.entity.Order
import com.example.food_delivery_app.core.orders.view.components.ItemCard
import com.example.food_delivery_app.core.orders.view.components.OrderNotConfirmed
import com.example.food_delivery_app.core.orders.viewModel.OrdersViewModel


@Composable
fun OrderNotConfirmedView(
    navController: NavController,
    ordersViewModel: OrdersViewModel,
    cart: Cart
) {
    val itemsList: List<Item> = emptyList()
    val itemCount = itemsList.size // Calcul du nombre d'éléments
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Composant OrderNotConfirmed
            OrderNotConfirmed(
                price = cart.totalPrice.toFloat(),
                onButton1Click = {
                    ordersViewModel.createOrder(
                        Order(
                            1,
                            cart.userId,
                            "Delivery Address",
                            "Notes",
                            cart.totalPrice,
                            "Pending"
                        )
                    )
                },// onCancelOrder,
                onButton2Click = {},// onConfirmOrder
            )

            Spacer(modifier = Modifier.height(12.dp)) // Espace entre OrderNotConfirmed et le texte

            // Texte affichant le nombre d'éléments
            Text(
                text = "Cart ($itemCount)",
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Liste des éléments (ItemCard)
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(itemsList) { item ->
                    ItemCard(
                        itemName = item.name,
                        price = item.price,
                        imageRes = item.imageRes,
                        noteTitle = "Note",
                        noteContent = item.noteContent,
                        onDeleteClick = { /* Gérer la suppression */ },
                        onBtnTextLessClick = { /* Gérer l'édition de la note */ },
                        initialValue = 1 // Valeur initiale par défaut
                    )
                }
            }
        }
}