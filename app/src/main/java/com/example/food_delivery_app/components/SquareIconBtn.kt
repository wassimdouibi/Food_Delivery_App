package com.example.food_delivery_app.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import java.time.format.TextStyle


@Composable
fun SquareIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonSize: ButtonSize = ButtonSize.MEDIUM,

    icon: ImageVector,
    iconDescription: String,

    contentColor: Color = LocalCustomColorScheme.current.ink50,
    containerColor: Color = LocalCustomColorScheme.current.primary400
) {
    // Set the button size based on the buttonSize parameter
    val size = when (buttonSize) {
        ButtonSize.LARGE -> 40.dp
        ButtonSize.MEDIUM -> 32.dp
        ButtonSize.SMALL -> 24.dp
    }

    Button(
        onClick = onClick,
        modifier = modifier
//            .size(size)
            .height(60.dp)
            .width(60.dp)
            .padding(0.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            containerColor = containerColor
        ),
        shape = RectangleShape
    ) {
        //Text(
         //   text = "+",
          //  fontSize = 16.dp
       // )
    }
}
