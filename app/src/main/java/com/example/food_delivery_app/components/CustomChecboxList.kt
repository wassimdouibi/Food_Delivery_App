package com.example.food_delivery_app.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme


@Composable
fun CustomCheckboxList(
    modifier: Modifier = Modifier,
    title: String,
    list: List<String>,
) {
    val checkedStates = remember {
        list.map {
            mutableStateOf(false)
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
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
                    checkedStates.forEach { it.value = false }
                },
                textStyle = LocalCustomTypographyScheme.current.p_smallBold,
                contentColor = LocalCustomColorScheme.current.utilityError,
            )
        }
//        Spacer(modifier = modifier.height(8.dp))
        Column{
            list.forEachIndexed { index, element ->
                element(
                    name = element,
                    checked = checkedStates[index]
                )
            }
        }
    }
}

@Composable
fun element(
    modifier: Modifier = Modifier,
    name: String,
    checked: MutableState<Boolean>,
){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(0.dp)
    ) {
        Box(
            modifier = Modifier.scale(0.85f)
        ) {
            Checkbox(
                checked = checked.value,
                onCheckedChange = { checked.value = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = LocalCustomColorScheme.current.primary500,
                    checkmarkColor = LocalCustomColorScheme.current.ink50,
                    uncheckedColor = Color(0xFFD0D5DD),
                )
            )
        }
        Text(
            text = name,
            style = LocalCustomTypographyScheme.current.p_small.copy()
        )
    }
}
