package com.example.food_delivery_app.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme


@Composable
fun Filter(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onRangeUpdated: (min: Int, max: Int) -> Unit
//    onListUpdated:
) {
    Column(
        modifier = modifier.wrapContentHeight().fillMaxWidth(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        RoundedIconBtn(
            onClick = onDismiss,
            icon = Icons.Default.Close,
            iconDescription = "Go back",
            containerColorEnabled = LocalCustomColorScheme.current.utilityError
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            Text(
                text = "Property Filter",
                style = LocalCustomTypographyScheme.current.heading4.copy(
                    color = LocalCustomColorScheme.current.ink500
                )
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
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
                    onRangeUpdated = onRangeUpdated
                )
            }
        }
    }
}
