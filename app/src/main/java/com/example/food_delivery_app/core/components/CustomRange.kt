package com.example.food_delivery_app.core.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme

@Composable
fun CustomRange(
    modifier: Modifier = Modifier,
    title: String,
    onRangeUpdated: (min: Int, max: Int) -> Unit
) {
    var sliderPosition =  remember { mutableStateOf(1f..5f) }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = title,
                style = LocalCustomTypographyScheme.current.p_mediumBold.copy(
                    color = LocalCustomColorScheme.current.ink500,
                )
            )
            BorderlessTextButton(
                textContent = "Reset",
                onClick = {
                    sliderPosition.value = 1f..5f
                },
                contentColor = LocalCustomColorScheme.current.utilityError,
                textStyle = LocalCustomTypographyScheme.current.p_smallBold
            )
        }
        Spacer(modifier = modifier.height(4.dp))
        Column {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${sliderPosition.value.start.toInt()} ${ if(sliderPosition.value.start.toInt() == 1) "star" else "stars"}",
                    style = TextStyle(
                        fontSize = LocalCustomTypographyScheme.current.p_small.fontSize,
                        fontWeight = FontWeight.Normal,
                        color = LocalCustomColorScheme.current.ink500
                    )
                )
                Text(
                    text = "${sliderPosition.value.endInclusive.toInt()} ${ if(sliderPosition.value.endInclusive.toInt() == 1) "star" else "stars"}",
                    style = TextStyle(
                        fontSize = LocalCustomTypographyScheme.current.p_small.fontSize,
                        fontWeight = FontWeight.Normal,
                        color = LocalCustomColorScheme.current.ink500
                    )
                )
            }
            RangeSlider(
                value = sliderPosition.value,
                steps = 3,
                onValueChange = { range -> sliderPosition.value = range },
                valueRange = 1f..5f,
                onValueChangeFinished = {
                    onRangeUpdated
                },
                colors = SliderDefaults.colors(
                    activeTrackColor = LocalCustomColorScheme.current.primary300,
                    inactiveTrackColor = LocalCustomColorScheme.current.primary300,
                    thumbColor = LocalCustomColorScheme.current.primary300
                )
            )
        }
    }
}