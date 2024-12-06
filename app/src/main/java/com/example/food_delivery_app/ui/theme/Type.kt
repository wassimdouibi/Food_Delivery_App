package com.example.food_delivery_app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.food_delivery_app.R

val urbanistFontFamily = FontFamily(
    Font(resId = R.font.urbanist_black, weight = FontWeight.Black),
    Font(resId = R.font.urbanist_regular, weight = FontWeight.Normal),
    Font(resId = R.font.urbanist_medium, weight = FontWeight.Medium),
    Font(resId = R.font.urbanist_semi_bold, weight = FontWeight.SemiBold),
    Font(resId = R.font.urbanist_bold, weight = FontWeight.Bold),
    Font(resId = R.font.urbanist_extra_bold, weight = FontWeight.ExtraBold),
)

val ralewayFontFamily = FontFamily(
    Font(resId = R.font.raleway_black, weight = FontWeight.Black),
    Font(resId = R.font.raleway_regular, weight = FontWeight.Normal),
    Font(resId = R.font.raleway_medium, weight = FontWeight.Medium),
    Font(resId = R.font.raleway_semi_bold, weight = FontWeight.SemiBold),
    Font(resId = R.font.raleway_bold, weight = FontWeight.Bold),
    Font(resId = R.font.raleway_extra_bold, weight = FontWeight.ExtraBold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    /*
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)