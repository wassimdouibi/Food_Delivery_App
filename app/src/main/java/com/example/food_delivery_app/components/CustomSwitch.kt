package com.example.food_delivery_app.components

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme

@Composable
fun CustomSwitch(
    value: Boolean,
    onCheckChange: (Boolean) -> Unit,
) {
    Switch(
        checked = value,
        onCheckedChange = {
                isChecked -> onCheckChange(isChecked)
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = LocalCustomColorScheme.current.ink50,
            checkedTrackColor = LocalCustomColorScheme.current.primary400,
            uncheckedThumbColor = LocalCustomColorScheme.current.ink50,
            uncheckedTrackColor = LocalCustomColorScheme.current.ink100,
            uncheckedBorderColor = LocalCustomColorScheme.current.ink200,
        )
    )
}