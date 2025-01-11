package com.example.food_delivery_app.auth.View.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.R
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme

@Composable
fun CheckBoxBtn(
    value: Boolean,
    onClick: () -> Unit
) {
    var value by remember {
        mutableStateOf(value)
    }

    Box(
        modifier = Modifier
            .size(20.dp)
            .border(
                width = 3.dp, color =  if(value) LocalCustomColorScheme.current.primary400 else LocalCustomColorScheme.current.ink400,
                shape = RoundedCornerShape(size = 3.dp)
            )
            .clickable {
                onClick
                value = !value
            }
            .clip(RoundedCornerShape(size = 3.dp))
            .background(if (value) LocalCustomColorScheme.current.primary400 else LocalCustomColorScheme.current.ink50),
        contentAlignment = Alignment.Center,
    ) {
        if (value) {
            Image(
                painter = painterResource(id = R.drawable.check),
                contentDescription = "",
                colorFilter = ColorFilter.tint(
                    LocalCustomColorScheme.current.ink50
                ),
                modifier = Modifier
                    .size(10.dp),
            )
        }
    }
}