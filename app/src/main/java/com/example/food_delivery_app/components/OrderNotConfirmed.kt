package com.example.food_delivery_app.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.R
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme


@SuppressLint("DefaultLocale")
@Composable
fun OrderNotConfirmed(
    price: Double,

    onButton1Click: () -> Unit,
    onButton2Click: () -> Unit,

    ghostButtonColor: Color = LocalCustomColorScheme.current.utilityError,
    filledBtnContentColor: Color = LocalCustomColorScheme.current.utilityWhiteBackground,
    filledButtonContainerColor: Color = LocalCustomColorScheme.current.primary400,
    cardColor: Color = LocalCustomColorScheme.current.utilityWhiteBackground
) {
    Card(
        modifier = Modifier.padding(16.dp),
        border = BorderStroke(1.dp, LocalCustomColorScheme.current.ink200),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(cardColor)
                .padding(16.dp)
        ) {
            // Ligne d'informations sur la commande
            OrderInfoRow(
                label = stringResource(R.string.total_order_price),
                content = String.format("%.2f DZD", price),

                icon = IconType.PainterIcon(
                    painter = painterResource(R.drawable.dollar),
                    iconDescription = "Price Icon"
                ),

                contentColor = LocalCustomColorScheme.current.ink50,
                containerColor = LocalCustomColorScheme.current.utilitySuccess
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Ligne des actions (boutons)
            OrderActionRow(
                button1Text = stringResource(R.string.cta_cancel),
                button2Text = stringResource(R.string.cta_confirm_order),

                onB1Click = onButton1Click,
                onB2Click = onButton2Click,

                ghostButtonColor = ghostButtonColor,
                filledButtonContentColor = filledBtnContentColor,
                filledButtonContainerColor = filledButtonContainerColor,
            )
        }
    }
}