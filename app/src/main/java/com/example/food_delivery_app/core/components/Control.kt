package com.example.food_delivery_app.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme


@Composable
fun CustomControl(
    modifier: Modifier = Modifier,
    options: List<String>,
    selectedIndex: Int,
    onOptionSelected: (Int) -> Unit
) {
    Surface(
        shadowElevation = 2.dp,
        shape = RoundedCornerShape(4.dp),
    ) {
        Box(
            modifier = modifier
                .wrapContentSize()
                .background(LocalCustomColorScheme.current.ink100),
            contentAlignment = Alignment.Center,
        ) {
            SingleChoiceSegmentedButton(
                modifier = modifier,
                options = options,
                selectedIndex = selectedIndex,
                onOptionSelected = { onOptionSelected(it) },
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleChoiceSegmentedButton(
    modifier:Modifier = Modifier,
    options: List<String>,
    selectedIndex: Int,
    onOptionSelected: (Int) -> Unit
) {
    SingleChoiceSegmentedButtonRow(
        modifier = modifier
            .fillMaxWidth(.75f)
            .border
                (
                width = 0.dp,
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 4.dp)
    ) {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = RoundedCornerShape(4.dp),
                onClick = { onOptionSelected(index) },
                selected = index == selectedIndex,
                label = {
                    Text(
                        label,
                        style = if( index == selectedIndex) LocalCustomTypographyScheme.current.p_smallBold else LocalCustomTypographyScheme.current.p_smallSemiBold
                    )
                },
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = LocalCustomColorScheme.current.primary400,
                    inactiveContainerColor = LocalCustomColorScheme.current.ink100,
                    activeContentColor = LocalCustomColorScheme.current.ink50,
                    inactiveContentColor = LocalCustomColorScheme.current.ink400
                ),
                icon = {},
                border = BorderStroke(0.dp, Color.Transparent),
            )
        }
    }
}