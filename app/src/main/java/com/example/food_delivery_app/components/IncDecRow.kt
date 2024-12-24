package com.example.food_delivery_app.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.R
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme


@Composable
fun IncrementDecrementRow(
    initialValue: Int = 0 ,// Valeur initiale du compteur
    modifier: Modifier = Modifier
) {
    // Déclaration explicite de la variable mutable pour stocker et mettre à jour la valeur
    val count = remember { mutableStateOf(initialValue) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween // Centrer les éléments horizontalement
    ) {
        // Bouton de décrémentation "-" avec une icône personnalisée (image minus)
        RectangularIconBtn(
            icon = painterResource(R.drawable.remove),
            iconDescription = "Decrement",
            onClick = { if (count.value > 0) count.value-- },
            contentColor = LocalCustomColorScheme.current.ink50,
            containerColor = LocalCustomColorScheme.current.primary400,
            size = 32.dp
        )

        // Affichage du chiffre actuel
        Text(
            text = count.value.toString(),
            style = LocalCustomTypographyScheme.current.heading5,
            color = LocalCustomColorScheme.current.ink400,
        )

        // Bouton d'incrémentation "+" avec une icône personnalisée (image plus)
        RectangularIconBtn(
            icon = Icons.Default.Add,
            iconDescription = "Increment",
            onClick = { count.value++ },
            contentColor = LocalCustomColorScheme.current.ink50,
            containerColor = LocalCustomColorScheme.current.primary400,
            size = 32.dp
        )
    }
}





//@Composable
//fun IncrementDecrementRow(
//    initialValue: Int = 0 ,// Valeur initiale du compteur
//    modifier: Modifier = Modifier
//) {
//    // Déclaration explicite de la variable mutable pour stocker et mettre à jour la valeur
//    val count = remember { mutableStateOf(initialValue) }
//
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.Center // Centrer les éléments horizontalement
//    ) {
//        // Bouton de décrémentation "-" avec une icône personnalisée (image minus)
//        IconButton(
//            onClick = { if (count.value > 0) count.value-- }, // Décrémenter le compteur, avec une limite à 0
//            modifier = Modifier.size(70.dp)
//        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.minus), // Utilisation de l'image minus
//                contentDescription = "Decrement",
//                modifier = Modifier.size(40.dp),
//            )
//        }
//
//        // Affichage du chiffre actuel
//        Text(
//            text = count.value.toString(),
//            style = MaterialTheme.typography.bodyLarge.copy(
//                fontWeight = FontWeight.Bold,
//                fontSize = 50.sp
//            ),
//            modifier = Modifier.padding(horizontal = 16.dp) // Espacement autour du texte
//        )
//
//        // Bouton d'incrémentation "+" avec une icône personnalisée (image plus)
//        IconButton(
//            onClick = { count.value++ }, // Incrémenter le compteur
//            modifier = Modifier.size(50.dp)
//        ){
//            Icon(
//                painter = painterResource(id = R.drawable.plus), // Utilisation de l'image plus
//                contentDescription = "Increment",
//                modifier = Modifier.size(40.dp),
//                tint = Color.Red
//            )
//        }
//    }
//}
