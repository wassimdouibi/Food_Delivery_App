package com.example.food_delivery_app.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.food_delivery_app.R

@Composable
fun DropdownMenuWithDetails() {
    var expanded = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
    ) {
        IconButton(onClick = { expanded.value = !expanded.value }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More options")
        }
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            // First section
            DropdownMenuItem(
                text = { Text(stringResource(R.string.security)) },
                leadingIcon = { Icon(Icons.Outlined.Warning, contentDescription = null) },
                onClick = { /* Do something... */ }
            )

            HorizontalDivider()

            // Second section
            DropdownMenuItem(
                text = { Text(stringResource(R.string.send_feedback)) },
                leadingIcon = { Icon(Icons.AutoMirrored.Outlined.Send, contentDescription = null) },
                onClick = { /* Do something... */ }
            )

            HorizontalDivider()

            // Third section
            DropdownMenuItem(
                text = { Text(stringResource(R.string.about)) },
                leadingIcon = { Icon(Icons.Outlined.Info, contentDescription = null) },
                onClick = { /* Do something... */ }
            )

            HorizontalDivider()

            DropdownMenuItem(
                text = { Text( stringResource(R.string.help)) },
                leadingIcon = { Icon(Icons.AutoMirrored.Outlined.Send, contentDescription = null) },
                onClick = { /* Do something... */ }
            )
        }
    }
}