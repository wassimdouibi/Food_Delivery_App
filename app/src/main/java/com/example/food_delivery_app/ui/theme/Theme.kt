package com.example.food_delivery_app.ui.theme

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


private val DarkColorScheme = darkColorScheme(
    primary = ReferenceOrange400,
    secondary = ReferenceOrange500
)
private val LightColorScheme = lightColorScheme(
    primary = ReferenceOrange400,
    secondary = ReferenceOrange500
)

val LocalCustomColorScheme = compositionLocalOf { defaultCustomColorScheme }
val LocalCustomTypographyScheme = compositionLocalOf { defaultCustomTypographyScheme }

@Composable
fun Food_Delivery_AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    CompositionLocalProvider(
        LocalCustomColorScheme provides defaultCustomColorScheme,
        LocalCustomTypographyScheme provides defaultCustomTypographyScheme
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}