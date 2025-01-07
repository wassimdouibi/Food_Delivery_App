package com.example.food_delivery_app.core.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.auth.data.entity.AuthPreferences
import com.example.food_delivery_app.auth.domain.AuthViewModel
import com.example.food_delivery_app.auth.presentation.components.BackUpBar
import com.example.food_delivery_app.components.*
import com.example.food_delivery_app.core.profile.domain.EditProfileViewModel
import com.example.food_delivery_app.navigation.Screen
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun EditProfileView(
    navController: NavController,
    authViewModel: AuthViewModel,
    editProfileViewModel: EditProfileViewModel = viewModel()
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    val context = LocalContext.current

//    // Collect user fields from EditProfileViewModel
//    val userFields by editProfileViewModel.userFields.collectAsState()
//
//    // Collect userId from AuthViewModel
//    val userId by authViewModel.userId.collectAsState()
//
//    // Effect to fetch user data when userId is available
//    LaunchedEffect(userId) {
////        userId?.let { id ->
////            editProfileViewModel.fetchUserFields(id)
////        }
//    }
//
//    // Effect to update the UI when user fields are received
//    LaunchedEffect(userFields) {
//        userFields?.let {
//            firstName = it.fullName.substringBefore(" ")
//            lastName = it.fullName.substringAfter(" ")
//            phoneNumber = it.phoneNumber
//        }
//
//        Toast.makeText(
//            context,
//            firstName,
//            Toast.LENGTH_SHORT
//        ).show()
//    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        BackUpBar(
            title = stringResource(R.string.edit_profile_title),
            navController = navController
        )

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.profile_pic),
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Fit
                )
                Image(
                    painter = painterResource(id = R.drawable.edit_square_bold),
                    contentDescription = "Edit picture",
                    colorFilter = ColorFilter.tint(LocalCustomColorScheme.current.primary400),
                    modifier = Modifier
                        .size(35.dp)
                        .align(Alignment.BottomEnd)
                        .offset(x = (-3).dp, y = (-4).dp)
                        .clickable {

                        }
                )
            }

            FoodDeliveryTextField(
                value = firstName,
                onValueChange = {
                    firstName = it
                },
                placeHolderText = stringResource(R.string.field_first_name),
                leadingIconId = R.drawable.profile_outline,
                leadingIconDescription = "Full name"
            )
            FoodDeliveryTextField(
                value = lastName,
                onValueChange = {
                    lastName = it
                },
                placeHolderText = stringResource(R.string.field_last_name),
                leadingIconId = R.drawable.message_outlined,
                leadingIconDescription = "Full name"
            )

            FoodDeliveryTextField(
                value = phoneNumber,
                onValueChange = {
                    phoneNumber = it
                },
                placeHolderText = stringResource(R.string.field_phone_number),
                leadingIconId = R.drawable.phone,
                leadingIconDescription = "Phone number",
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            FilledTextButton(
                textContent = stringResource(R.string.update_profile_infos),
                textStyle = LocalCustomTypographyScheme.current.p_mediumBold,
                modifier = Modifier.padding(),
                onClick = {
//                    navController.popBackStack()
                    navController.navigate(Screen.ProfileView.route)

                },
            )
        }

        Spacer(modifier = Modifier.weight(1f))

    }
}