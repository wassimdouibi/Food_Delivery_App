package com.example.food_delivery_app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodDeliveryTextField(
    value: String,
    placeholder: @Composable (() -> Unit)? = null,
    placeHolderText: String = "",
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    leadingIconId: Int? = null,
    leadingIconDescription: String = "",
    trailingIcon: @Composable (() -> Unit)? = null,
    trailingIconId: Int? = null,
    trailingIconDescription: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RoundedCornerShape(size = 8.dp),
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(),
    modifier: Modifier = Modifier,
) {
    var isFocused by remember { mutableStateOf(false) }

    androidx.compose.material3.TextField(value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeHolderText,
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        textStyle = MaterialTheme.typography.labelLarge.merge(textStyle),
        leadingIcon = {
            if (leadingIconId != null) Image(
                painter = painterResource(id = leadingIconId),
                contentDescription = leadingIconDescription,
                colorFilter = ColorFilter.tint(
                    if (isFocused)
                        LocalCustomColorScheme.current.primary500
                    else if (value.isNotEmpty())
                        LocalCustomColorScheme.current.ink500
                    else
                        LocalCustomColorScheme.current.ink100
                ),
                modifier = Modifier.size(20.dp),
            ) else if (leadingIcon != null) leadingIconId
        },
        trailingIcon = {
            if (trailingIconId != null) Image(
                painter = painterResource(id = trailingIconId),
                contentDescription = trailingIconDescription,
                colorFilter = ColorFilter.tint(
                    if (isFocused)
                        LocalCustomColorScheme.current.primary500
                    else if (value.isNotEmpty())
                        LocalCustomColorScheme.current.ink500
                    else
                        LocalCustomColorScheme.current.ink100
                ),
                modifier = Modifier.size(20.dp),
            ) else {
            }
        },
        maxLines = maxLines,
        enabled = enabled,
        readOnly = readOnly,
        label = label,
        supportingText = supportingText,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        interactionSource = interactionSource,
        shape = shape,
        colors = TextFieldDefaults.textFieldColors(
            containerColor =
            if (isFocused)
                LocalCustomColorScheme.current.primary500
            else
                LocalCustomColorScheme.current.ink100,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
        ).apply { colors },
        modifier = Modifier
            .border(
                color =
                if (isFocused)
                    LocalCustomColorScheme.current.primary500
                else
                    LocalCustomColorScheme.current.ink100,
                width = if (isFocused) 2.dp else 0.dp,
                shape = RoundedCornerShape(size = 10.dp)
            )
            .clip(shape)
            .fillMaxWidth()
            .onFocusChanged {
                isFocused = it.isFocused
            }
            .height(58.dp)
            .then(modifier))
}
