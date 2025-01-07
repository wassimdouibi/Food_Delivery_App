package com.example.food_delivery_app.ui.theme
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColorScheme(
    // -------------- Primary -------------------
    val primary50: Color,
    val primary100: Color,
    val primary200: Color,
    val primary300: Color,
    val primary400: Color,
    val primary500: Color,
    val primary600: Color,
    val primary700: Color,
    val primary800: Color,
    val primary900: Color,
    val primary950: Color,

    // -------------- Reference / Base -------------------
    val baseWhite: Color,
    val baseBlack: Color,

    // -------------- Reference / Ink -------------------
    val ink50: Color,
    val ink100: Color,
    val ink200: Color,
    val ink300: Color,
    val ink400: Color,
    val ink500: Color,

    // -------------- Accent -------------------
    val accentOrange: Color,
    val accentBlue: Color,
    val accentYellow: Color,
    val accentGreen: Color,
    val accentPurple: Color,
    val accentPink: Color,

    // -------------- Utility -------------------
    val utilityActive: Color,
    val utilityInfo: Color,
    val utilityWarning: Color,
    val utilityError: Color,
    val utilitySuccess: Color,
    val utilityDarkBackground: Color,
    val utilityWhiteBackground: Color
)


val defaultCustomColorScheme = CustomColorScheme(
    primary50 = ReferenceOrange50,
    primary100 = ReferenceOrange100,
    primary200 = ReferenceOrange200,
    primary300 = ReferenceOrange300,
    primary400 = ReferenceOrange400,
    primary500 = ReferenceOrange500,
    primary600 = ReferenceOrange600,
    primary700 = ReferenceOrange700,
    primary800 = ReferenceOrange800,
    primary900 = ReferenceOrange900,
    primary950 = ReferenceOrange950,

    baseWhite = ReferenceBaseWhite,
    baseBlack = ReferenceBaseBlack,

    ink50 = ReferenceInk50,
    ink100 = ReferenceInk100,
    ink200 = ReferenceInk200,
    ink300 = ReferenceInk300,
    ink400 = ReferenceInk400,
    ink500 = ReferenceInk500,

    accentOrange = AccentOrange,
    accentBlue = AccentBlue,
    accentYellow = AccentYellow,
    accentGreen = AccentGreen,
    accentPurple = AccentPurple,
    accentPink = AccentPink,

    utilityActive = UtilityActive,
    utilityInfo = UtilityInfo,
    utilityWarning = UtilityWarning,
    utilityError = UtilityError,
    utilitySuccess = UtilitySuccess,
    utilityDarkBackground = UtilityDarkBackground,
    utilityWhiteBackground = UtilityWhiteBackground
)