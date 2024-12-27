package com.example.parkir.views.core.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.R
import com.example.food_delivery_app.auth.presentation.components.BackUpBar
import com.example.food_delivery_app.components.FilledTextButton
import com.example.food_delivery_app.components.FoodDeliveryTextField
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme



@Composable
fun EditProfileView(
//    navController: NavHostController
) {
    var fullName by remember {
        mutableStateOf("")
    }
    var birthDate by remember {
        mutableStateOf("")
    }
    var email = "ka_kenniche@esi.dz"
    var phoneNumber by remember {
        mutableStateOf("")
    }
    var gender by remember {
        mutableStateOf("")
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
//            navController = navController
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
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
                value = fullName,
                onValueChange = {
                    fullName = it
                },
                placeHolderText = stringResource(R.string.field_first_name),
                leadingIconId = R.drawable.profile_outline,
                leadingIconDescription = "Full name"
            )
            FoodDeliveryTextField(
                value = fullName,
                onValueChange = {
                    fullName = it
                },
                placeHolderText = stringResource(R.string.field_last_name),
                leadingIconId = R.drawable.message_outlined,
                leadingIconDescription = "Full name"
            )

            FoodDeliveryTextField(
                value = birthDate,
                onValueChange = {},
                readOnly = true,
                placeHolderText = stringResource(R.string.field_birthday),
                leadingIconId = R.drawable.calendar,
                leadingIconDescription = "Date of birth",
                modifier = Modifier.clickable {}
            )

            FoodDeliveryTextField(
                value = email,
                onValueChange = {},
                placeHolderText = stringResource(R.string.field_email),
                readOnly = true,
                leadingIconId = R.drawable.message_outlined,
                leadingIconDescription = "Email",
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            FilledTextButton(
                textContent = stringResource(R.string.update_profile_infos),
                textStyle = LocalCustomTypographyScheme.current.p_mediumBold,
                modifier = Modifier.padding(),
                onClick = {
//                navController.popBackStack()
                },
            )
        }

    }
}

