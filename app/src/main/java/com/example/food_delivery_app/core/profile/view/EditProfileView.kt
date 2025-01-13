package com.example.food_delivery_app.core.profile.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.auth.model.service.response.UserFieldResponse
import com.example.food_delivery_app.auth.viewModel.AuthViewModel
import com.example.food_delivery_app.auth.view.components.BackUpBar
import com.example.food_delivery_app.core.profile.viewmodel.ProfileViewModel
import com.example.food_delivery_app.core.components.FilledTextButton
import com.example.food_delivery_app.core.components.FoodDeliveryTextField
import com.example.food_delivery_app.router.Router
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme
import kotlinx.coroutines.launch


@Composable
fun EditProfileView(
    navController: NavController,
    authViewModel: AuthViewModel,
    profileViewModel: ProfileViewModel,
    isFromSignup: Boolean = false
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phonenumber by remember { mutableStateOf("") }
    var profilePicture by remember { mutableStateOf("") }
    val context = LocalContext.current

    // Collect all states
    val userId: String? by authViewModel.userId.collectAsState(initial = null)
    val userFields: UserFieldResponse? by profileViewModel.userFields.collectAsState(initial = null)
    val isLoading: Boolean by profileViewModel.isLoading.collectAsState(initial = false)
    val error: String? by profileViewModel.error.collectAsState(initial = null)


    // Only fetch user data if NOT coming from signup
    LaunchedEffect(1) {
        if (!isFromSignup) {
            userId?.let { id ->
                profileViewModel.getUserFields(id)
            }
        }
    }

    // Update UI when user fields are received (only if not from signup)
    LaunchedEffect(userFields) {
        if (!isFromSignup) {
            userFields?.let { fields ->
                fields.name?.let { fullName ->
                    firstName = fullName.substringBefore(" ", "")
                    lastName = fullName.substringAfter(" ", "")
                }
                phonenumber = fields.phonenumber ?: ""
                profilePicture = fields.profilepicture ?: ""
            }
        }
    }


    //  Handle error messages
//    LaunchedEffect(error) {
//        error?.let {
//            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
//        }
//    }

    // Show loading state
    if (isLoading) {
        CircularProgressIndicator()
    }

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
                value = phonenumber,
                onValueChange = {
                    phonenumber = it
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
                    if(firstName != "" && lastName != "") {
                        userId?.let { id ->
                            Log.d("Heeere", "Full name modification ; user id is : $id")
                            profileViewModel.updateUserName(id, "$firstName $lastName")
                        }
                    }
                    if(phonenumber != "") {
                        userId?.let { id ->
                            Log.d("Heeere", "Phone number modification ; user id is : $id")
                            profileViewModel.updateUserPhoneNumber(id, phonenumber)
                        }
                    }
                    if(profilePicture != ""){
                        userId?.let { id ->
                            Log.d("Heeere", "Profile picture modification ; user id is : $id")
                            profileViewModel.updateProfilePicture(id, profilePicture)
                        }
                    }
                    userId?.let { id ->
                        profileViewModel.getUserFields(id)
                    }
                    navController.navigate(Router.ProfileScreen.route)
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

    }
}