package com.example.food_delivery_app.core.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.R
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme


@SuppressLint("DefaultLocale")
@Composable
fun OrderConfirmed(
    orderLoc: String,
    deliveryLoc: String,
    price: Float,

    onButton1Click: () -> Unit,
    onButton2Click: () -> Unit,

    cardColor: Color,
    ghostBtnColor: Color = LocalCustomColorScheme.current.utilityError,
    filledBtnContentColor : Color = LocalCustomColorScheme.current.utilityWhiteBackground,
    filledBtnContainerColor : Color = LocalCustomColorScheme.current.primary400


) {
    val orderStatus = remember { mutableStateOf("Not Confirmed") }

    Card(
        modifier = Modifier.padding(16.dp),
        border = BorderStroke(1.dp, LocalCustomColorScheme.current.ink200),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(cardColor)
                .padding(16.dp),
        ) {
            // Informations de la commande
            OrderInfoRow(
                label = stringResource(R.string.order_location),
                content = orderLoc,

                icon = IconType.VectorIcon(
                    imageVector = Icons.Default.Check,
                    iconDescription = "Check Icon"
                ),

                contentColor = LocalCustomColorScheme.current.ink50,
                containerColor = LocalCustomColorScheme.current.utilitySuccess
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Dot(height = 6.dp)
                Dot(height = 10.dp)
                Dot(height = 6.dp)
            }

            OrderInfoRow(
                label = stringResource(R.string.delivery_location),
                content = deliveryLoc,

                icon = IconType.VectorIcon(
                    imageVector = Icons.Default.LocationOn,
                    iconDescription = "Delivery Location Icon"
                ),

                contentColor = LocalCustomColorScheme.current.ink50,
                containerColor = LocalCustomColorScheme.current.utilitySuccess
            )

            Spacer(modifier = Modifier.height(28.dp))

            OrderInfoRow(
                label = stringResource(R.string.order_price),
                content = String.format("%.2f DZD", price),

                icon = IconType.PainterIcon(
                    painter = painterResource(R.drawable.dollar),
                    iconDescription = "Order Price"
                ),

                contentColor = LocalCustomColorScheme.current.ink50,
                containerColor = LocalCustomColorScheme.current.utilitySuccess
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Boutons d'action avec OrderActionRow
            OrderActionRow(
                button1Text = stringResource(R.string.cta_cancel),
                button2Text = stringResource(R.string.cta_track_order),

                onB1Click = onButton1Click,
                onB2Click = onButton2Click,

                ghostButtonColor = ghostBtnColor,
                filledButtonContentColor = filledBtnContentColor,
                filledButtonContainerColor = filledBtnContainerColor,
            )
        }
    }
}