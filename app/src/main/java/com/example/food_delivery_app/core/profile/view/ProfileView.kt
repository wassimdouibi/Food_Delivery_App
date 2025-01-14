package com.example.food_delivery_app.core.profile.view

import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.auth.viewModel.AuthViewModel
import com.example.food_delivery_app.core.profile.viewmodel.ProfileViewModel
import com.example.food_delivery_app.core.components.BorderlessTextButton
import com.example.food_delivery_app.core.components.CustomSwitch
import com.example.food_delivery_app.core.components.DropdownMenuWithDetails
import com.example.food_delivery_app.router.Router
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme


@Composable
fun ProfileView(
    navController: NavController,
    authViewModel: AuthViewModel,
    profileViewModel: ProfileViewModel,
    pref: SharedPreferences
) {
    val scope = rememberCoroutineScope()

    var ConfirmLogoutAlertBox by rememberSaveable {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val isLoading by profileViewModel.isLoading.collectAsState()
    val error by profileViewModel.error.collectAsState()

    val userId by authViewModel.userId.collectAsState()
    val userFields by profileViewModel.userFields.collectAsState()

    val darkThemeColor = rememberSaveable { mutableStateOf(false) }
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var profilepicture by remember { mutableStateOf("") }


    // Fetch user data when userId is available
    LaunchedEffect(1) {
        userId?.let { id ->
            profileViewModel.getUserFields(id)
        }
    }
    // Handle error messages
    LaunchedEffect(error) {
        error?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    // Update UI when user fields are received
    LaunchedEffect(userFields) {
        userFields?.let { fields ->
            fullName = fields.name ?: "Full Name"
            email = fields.email ?: "Email"
            profilepicture = fields.profilepicture ?: ""
        }
    }

    // Show loading state
    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
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
                    text = fullName,
                    style = LocalCustomTypographyScheme.current.p_largeBold
                )
                Text(
                    text = email,
                    style = LocalCustomTypographyScheme.current.p_medium
                )
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 24.dp,),)


            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                ProfileItem(
                    title = stringResource(R.string.edit_profile_title),
                    icon = R.drawable.profile_outline
                ) {
                    navController.navigate(Router.EditProfileScreen.createRoute())
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
                    navController.navigate(Router.NotificationsSettingsScreen.route)
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
                onLogout = {
                    authViewModel.logout(pref)
                    navController.navigate(Router.LoginScreen.route)
                    ConfirmLogoutAlertBox = false
                },
                onCancel = {
                    ConfirmLogoutAlertBox = false
                }
            )
        }
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
                text = stringResource(R.string.logout),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = LocalCustomColorScheme.current.utilityError,
                style = LocalCustomTypographyScheme.current.heading3
            )
        },
        text = {
            Text(
                text = stringResource(R.string.confirm_logout),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = LocalCustomTypographyScheme.current.p_medium
            )
        },
        confirmButton = {
            BorderlessTextButton(
                textContent = stringResource(R.string.logout),
                textStyle = LocalCustomTypographyScheme.current.p_mediumBold,
                contentColor = LocalCustomColorScheme.current.utilityError,
                onClick = {
                    onLogout()
                }
            )
        },
        dismissButton = {
            BorderlessTextButton(
                textContent = stringResource(R.string.cta_cancel),
                textStyle = LocalCustomTypographyScheme.current.p_mediumBold,
                onClick = { onCancel() },
                contentColor = LocalCustomColorScheme.current.utilityError,
            )
        }
    )
}