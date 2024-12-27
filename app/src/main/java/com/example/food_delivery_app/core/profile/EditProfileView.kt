package com.example.food_delivery_app.core.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.R
import com.example.food_delivery_app.components.ConfirmLogoutAlertBox
import com.example.food_delivery_app.components.CustomSwitch
import com.example.food_delivery_app.components.DropdownMenuWithDetails
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme

@Composable
fun ProfileView(
//    navController: NavHostController
) {
    val scope = rememberCoroutineScope()

    var ConfirmLogoutAlertBox by rememberSaveable {
        mutableStateOf(false)
    }

    val darkThemeColor = rememberSaveable { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stringResource(R.string.profile_title),
                style = LocalCustomTypographyScheme.current.heading3
            )
            DropdownMenuWithDetails()
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_pic),
                contentDescription = "Profile pic",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
            )
            Text(
                text = "DOUIBI Wassim",
                style = LocalCustomTypographyScheme.current.p_largeBold
            )
            Text(
                text = "lw_douibi@esi.dz",
                style = LocalCustomTypographyScheme.current.p_medium
            )
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 24.dp,),)


        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            ProfileItem(
                title = stringResource(R.string.edit_profile),
                icon = R.drawable.profile_outline
            ) {
//                navController.navigate(Router.EditProfileScreen.route)
            }

            ProfileItem(
                title = stringResource(R.string.payement),
                icon = R.drawable.wallet_outlined
            ) {

            }

            ProfileItem(
                title = stringResource(R.string.notifications),
                icon = R.drawable.notification_outlined
            ) {
//                navController.navigate(Router.NotificationsSettingsScreen.route)
            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.show_outlined),
                    contentDescription = "Themes",
                    colorFilter = ColorFilter.tint(LocalCustomColorScheme.current.ink500),
                    modifier = Modifier.size(32.dp),
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = stringResource(R.string.theme),
                    style = LocalCustomTypographyScheme.current.heading5,
                    color = LocalCustomColorScheme.current.ink500
                )
                Spacer(modifier = Modifier.weight(1f))
                CustomSwitch(
                    value = darkThemeColor.value,
                    onCheckChange = {darkThemeColor.value = it},
                )
            }

            ProfileItem(
                title = stringResource(R.string.logout),
                icon = R.drawable.logout_outlined, color = LocalCustomColorScheme.current.utilityError
            ) {
                ConfirmLogoutAlertBox = true
            }

        }

    }

    if (ConfirmLogoutAlertBox) {
        ConfirmLogoutAlertBox(
            onLogout = {},
            onCancel = { ConfirmLogoutAlertBox = false }
        )
    }



}



@Composable
fun ProfileItem(
    title: String,
    icon: Int,
    color: Color = LocalCustomColorScheme.current.ink500,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = title,
            colorFilter = ColorFilter.tint(color),
            modifier = Modifier.size(32.dp),
        )
        Text(
            text = title,
            style = LocalCustomTypographyScheme.current.heading5,
            color = color
        )
    }
}