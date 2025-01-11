package com.example.food_delivery_app.auth.View.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.R
import com.example.food_delivery_app.core.components.FilledTextButton
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme

@Composable
fun ResetPasswordSuccessBox(
    onClick: () -> Unit,
) {
    AlertDialog(
        containerColor = LocalCustomColorScheme.current.utilityWhiteBackground,
        onDismissRequest = {},
        tonalElevation = 100.dp,
        shape = RoundedCornerShape(8.dp),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                        .background(LocalCustomColorScheme.current.primary500)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.check_square_bold),
                        contentDescription = "Success",
                        colorFilter = ColorFilter.tint(
                            LocalCustomColorScheme.current.utilityWhiteBackground
                        ),
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.Center)

                    )
                }
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.congratulations),
                    style = LocalCustomTypographyScheme.current.heading4,
                    color = LocalCustomColorScheme.current.primary500,
                )
                Text(
                    text = stringResource(R.string.modification_with_success),
                    style = LocalCustomTypographyScheme.current.p_medium,
                    color = Color(0xFF72777A),
                )
            }
        },
        confirmButton = {
            FilledTextButton(
                textContent = stringResource(R.string.cta_go_back_to_login),
                textStyle = LocalCustomTypographyScheme.current.p_mediumBold,
                modifier = Modifier.fillMaxWidth(),
                onClick = onClick
            )
        }
    )
}