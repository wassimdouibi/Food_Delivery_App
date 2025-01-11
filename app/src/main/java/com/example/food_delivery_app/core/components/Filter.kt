package com.example.food_delivery_app.core.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme


@Composable
fun Filter(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
//    onRangeUpdated: (min: Int, max: Int) -> Unit
//    onListUpdated:
) {
    var min: Int
    var max: Int

    Column(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Property Filter",
                style = LocalCustomTypographyScheme.current.heading4,
                color = LocalCustomColorScheme.current.ink500,
                textAlign = TextAlign.Start,
            )

            RoundedIconBtn(
                onClick = onDismiss,
                icon = Icons.Default.Close,
                iconDescription = "Go back",
                containerColorEnabled = LocalCustomColorScheme.current.utilityError
            )
        }


        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            CustomCheckboxList(
                modifier = modifier,
                list = listOf("Algerian", "Brazil", "USA"),
                title = "Cuisine type"
            )
            CustomRange(
                modifier = modifier.fillMaxWidth(),
                title = "Rating range",
                onRangeUpdated = { min, max ->
                    {

                    }
                }
            )
        }
    }
}
