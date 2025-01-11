package com.example.food_delivery_app.core.profile.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_delivery_app.auth.View.components.BackUpBar
import com.example.food_delivery_app.core.components.CustomSwitch
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme


@Composable
fun NotificationsSettingsView(
    navController: NavController
) {
    var vibrate = remember { mutableStateOf(false) }
    var generalNotifications = remember { mutableStateOf(false) }
    var sound = remember { mutableStateOf(false) }
    var appUpdates = remember { mutableStateOf(false) }
    var newServiceAvailable = remember { mutableStateOf(false) }
    var newTipAvailable = remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        BackUpBar(
            title = "Notifications",
            navController = navController
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "General Notifications",
                    style = LocalCustomTypographyScheme.current.heading5.copy(
                        fontWeight = FontWeight.Normal,
                    )
                )


                CustomSwitch(
                    value = generalNotifications.value,
                    onCheckChange = { generalNotifications.value = it },
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Sound",
                    style = LocalCustomTypographyScheme.current.heading5.copy(
                        fontWeight = FontWeight.Normal,
                    )
                )


                CustomSwitch(
                    value = sound.value,
                    onCheckChange = {
                        sound.value = it
                    },
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Vibrate",
                    style = LocalCustomTypographyScheme.current.heading5.copy(
                        fontWeight = FontWeight.Normal,
                    )
                )


                CustomSwitch(
                    value = vibrate.value,
                    onCheckChange = { vibrate.value = it },
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "App Updates",
                    style = LocalCustomTypographyScheme.current.heading5.copy(
                        fontWeight = FontWeight.Normal,
                    )
                )


                CustomSwitch(
                    value = appUpdates.value,
                    onCheckChange = { appUpdates.value = it },
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "New Service Available ",
                    style = LocalCustomTypographyScheme.current.heading5.copy(
                        fontWeight = FontWeight.Normal,
                    )
                )


                CustomSwitch(
                    value = newServiceAvailable.value,
                    onCheckChange = { newServiceAvailable.value = it },
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "New Tip Available",
                    style = LocalCustomTypographyScheme.current.heading5.copy(
                        fontWeight = FontWeight.Normal,
                    )
                )


                CustomSwitch(
                    value = newTipAvailable.value,
                    onCheckChange = { newTipAvailable.value = it },
                )
            }
        }
    }
}