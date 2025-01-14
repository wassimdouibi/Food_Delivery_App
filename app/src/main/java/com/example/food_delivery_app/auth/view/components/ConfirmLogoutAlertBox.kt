package com.example.food_delivery_app.auth.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.core.components.BorderlessTextButton
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme

@Composable
fun ConfirmLogoutAlertBox(
    onLogout: () -> Unit,
    onCancel: () -> Unit
) {
    val scope = rememberCoroutineScope()

    AlertDialog(
        containerColor = LocalCustomColorScheme.current.utilityWhiteBackground,
        onDismissRequest = { onCancel() },
        tonalElevation = 100.dp,
        shape = RoundedCornerShape(8.dp),
        title = {
            Text(
                text = "Logout",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = LocalCustomColorScheme.current.utilityError,
                style = LocalCustomTypographyScheme.current.heading3
            )
        },
        text = {
            Text(
                text = "Are you sure you want to logout?",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = LocalCustomTypographyScheme.current.p_medium
            )
        },
        confirmButton = {
            BorderlessTextButton(
                textContent = "Logout",
                textStyle = LocalCustomTypographyScheme.current.p_mediumBold,
                contentColor = LocalCustomColorScheme.current.utilityError,
                onClick = {
                    onLogout()
                }
            )
        },
        dismissButton = {
            BorderlessTextButton(
                textContent = "Cancel",
                textStyle = LocalCustomTypographyScheme.current.p_mediumBold,
                onClick = { onCancel() },
                contentColor = LocalCustomColorScheme.current.utilityError,
            )
        }
    )
}