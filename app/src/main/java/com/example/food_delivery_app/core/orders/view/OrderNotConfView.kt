package com.example.food_delivery_app.core.Orders.View

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.food_delivery_app.core.BottomBar
import com.example.food_delivery_app.core.components.Item
import com.example.food_delivery_app.core.orders.view.components.ItemCard
import com.example.food_delivery_app.core.orders.view.components.OrderNotConfirmed


@Composable
fun OrderNotConfirmedView(
    navController: NavController,
) {
    val itemsList: List<Item> = emptyList()
    val totalPrice: Float = itemsList.fold(0.0f) { total, item -> total + item.price }
    val itemCount = itemsList.size // Calcul du nombre d'éléments

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Composant OrderNotConfirmed
            OrderNotConfirmed(
                price = totalPrice,
                onButton1Click = {},// onCancelOrder,
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
}