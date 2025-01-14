package com.example.food_delivery_app.core.orders.view.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.core.components.BorderlessTextButton
import com.example.food_delivery_app.core.components.IncrementDecrementRow
import com.example.food_delivery_app.core.components.Note
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme

@Composable
fun ItemCard(
    itemName: String, // Nom de l'élément dans ItemInfoRow
    price: Float, // Prix de l'élément dans ItemInfoRow
    imageRes: Int, // Image de l'élément dans ItemInfoRow
    noteTitle: String, // Titre de la note
    noteContent: String?, // Contenu de la note, qui peut être null
    onDeleteClick: () -> Unit, // Fonction de suppression dans ItemInfoRow
    onBtnTextLessClick: () -> Unit, // Fonction pour le bouton TextLess
    initialValue: Int = 0, // Valeur initiale pour IncrementDecrementRow
    modifier:Modifier = Modifier,
    containerColor: Color = LocalCustomColorScheme.current.utilityWhiteBackground,
) {
    // Conteneur Card
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, LocalCustomColorScheme.current.ink100),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = containerColor),
    ) {
        // Contenu de la carte
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Appel de ItemInfoRow
            ItemInfoRow(
                text1 = itemName,
                price = price,
                imageRes1 = imageRes,
                onDeleteClick = onDeleteClick
            )

            // Appel de Note
            Note(
                title = noteTitle,
                content = noteContent ?: "" // Si noteContent est null, on passe une chaîne vide
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Appel de BorderlessTextButton
                BorderlessTextButton(
                    onClick = onBtnTextLessClick,
                    textContent = "Modify Note",
                    textStyle = LocalCustomTypographyScheme.current.p_mediumBold
                )
                Spacer(modifier = Modifier.weight(1f))
                // Appel de IncrementDecrementRow
                IncrementDecrementRow(
                    modifier = Modifier.weight(1f),
                    initialValue = initialValue
                )
            }

        }
    }
}

