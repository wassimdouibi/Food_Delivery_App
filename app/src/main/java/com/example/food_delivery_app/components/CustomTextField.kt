package com.example.food_delivery_app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.vector.DefaultTintColor
import androidx.compose.ui.graphics.vector.ImageVector
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
    leadingIconVector: ImageVector? = null,
    leadingIconDescription: String = "",
    trailingIcon: @Composable (() -> Unit)? = null,
    trailingIconId: Int? = null,
    trailingIconVector: ImageVector? = null,
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
    changeShowFilterState: () -> Unit = {}
) {
    var isFocused by remember { mutableStateOf(false) }

    TextField(

        value = value,

        onValueChange = onValueChange,

        placeholder = {
            Text(
                text = placeHolderText,
                style = MaterialTheme.typography.bodyMedium,
            )
        },

        textStyle = MaterialTheme.typography.labelLarge.merge(textStyle),




        leadingIcon = {
            if (leadingIconId != null)
                Image(
                    painter = painterResource(id = leadingIconId),
                    contentDescription = leadingIconDescription,
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(LocalCustomColorScheme.current.primary400)

                )
            else if (leadingIcon != null)
                leadingIconId
            else if (leadingIconVector != null)
                Icon(
                    imageVector = leadingIconVector,
                    contentDescription = leadingIconDescription,
                    modifier = Modifier.size(24.dp),
                    tint = LocalCustomColorScheme.current.primary400
                )
        },
        trailingIcon = {
            if (trailingIconId != null)
                Image(
                    painter = painterResource(id = trailingIconId),
                    contentDescription = trailingIconDescription,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            changeShowFilterState()
                        },
                    colorFilter = ColorFilter.tint(LocalCustomColorScheme.current.primary400)
                )
            else if (trailingIconVector != null)
                Icon(
                    imageVector = trailingIconVector,
                    contentDescription = leadingIconDescription,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            changeShowFilterState()
                        },
                    tint = LocalCustomColorScheme.current.primary400
                )
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
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
        ).apply { colors },
        modifier = Modifier
            .clip(shape)
            .onFocusChanged { isFocused = it.isFocused }
            .border(
                color =
                if (isFocused)
                    LocalCustomColorScheme.current.primary400
                else
                    LocalCustomColorScheme.current.ink200,
                width = 2.dp,
                shape = RoundedCornerShape(size = 8.dp)
            )
            .then(modifier)
    )
}