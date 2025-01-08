package com.example.food_delivery_app

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.food_delivery_app.components.*


@Composable
fun ItemsScreen(
    navController: NavHostController,
    itemsList: List<Item>, // Liste des éléments passée en paramètre
    onConfirmOrder: () -> Unit,
    onCancelOrder: () -> Unit
) {
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
                onButton1Click = onCancelOrder,
                onButton2Click = onConfirmOrder
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

        // Barre de navigation en bas
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
//import com.example.food_delivery_app.components.Item
//import com.example.food_delivery_app.components.Order
//import com.example.food_delivery_app.ui.theme.Food_Delivery_AppTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge() // If required for edge-to-edge layout
//        setContent {
//            Food_Delivery_AppTheme {
//                val navController = rememberNavController()
//
//    // Données de test pour les items
//    val testItems = listOf(
//        Item(
//            name = "Pizza",
//            price = 1200f,
//            imageRes = R.drawable.logo, // Remplacez par une ressource drawable existante
//            noteContent = "Ajoutez plus de fromage"
//        ),
//        Item(
//            name = "Burger",
//            price = 800f,
//            imageRes = R.drawable.logo,
//            noteContent = "Pas de ketchup"
//        ),
//        Item(
//            name = "Pasta",
//            price = 1000f,
//            imageRes = R.drawable.logo,
//            noteContent = null // Pas de contenu pour cette note
//        ),
//        Item(
//            name = "Sushi",
//            price = 1500f,
//            imageRes = R.drawable.logo,
//            noteContent = "Ajoutez du wasabi"
//        )
//    )
//
//    // Appel de ItemsScreen avec des données simulées
//    ItemsScreen(
//        navController = navController,
//        itemsList = testItems,
//        onConfirmOrder = {
//            println("Commande confirmée avec un total de ")
//        },
//        onCancelOrder = {
//            println("Commande annulée.")
//        }
//    )
//
//            }
//            }
//        }
//    }
//
//



//
//data class Item(
//    val name: String,
//    val price: Float,
//    @DrawableRes val imageRes: Int, // Référence de l'image
//    val noteContent: String? = null // Contenu de la note (peut être null)
//)
