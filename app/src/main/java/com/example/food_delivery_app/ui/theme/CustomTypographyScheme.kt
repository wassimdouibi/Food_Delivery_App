package com.example.food_delivery_app.ui.theme
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class CustomTypographyScheme(
    // ------- Heading --------
    val heading0 : TextStyle,
    val heading1 : TextStyle,
    val heading2 : TextStyle,
    val heading3 : TextStyle,
    val heading4 : TextStyle,
    val heading5 : TextStyle,

    // ------- Paragraph --------
    val p_large : TextStyle,
    val p_largeBold : TextStyle,
    val p_medium : TextStyle,
    val p_mediumBold : TextStyle,
    val p_small : TextStyle,
    val p_smallBold : TextStyle,
    val p_smallSemiBold : TextStyle
)

val defaultCustomTypographyScheme = CustomTypographyScheme(
    heading0 = TextStyle(
        fontFamily = urbanistFontFamily,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold
    ),
    heading1 = TextStyle(
        fontFamily = urbanistFontFamily,
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold
    ),
    heading2 = TextStyle(
        fontFamily = urbanistFontFamily,
        fontSize = 32.sp,
        fontWeight = FontWeight.Normal
    ),
    heading3 = TextStyle(
        fontFamily = urbanistFontFamily,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold
    ),
    heading4 = TextStyle(
        fontFamily = urbanistFontFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    ),
    heading5 = TextStyle(
        fontFamily = urbanistFontFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    ),

    p_large = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal
    ),
    p_largeBold = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    ),
    p_medium = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    p_mediumBold = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    ),
    p_small = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    p_smallBold = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    ),
    p_smallSemiBold = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold
    )
)
