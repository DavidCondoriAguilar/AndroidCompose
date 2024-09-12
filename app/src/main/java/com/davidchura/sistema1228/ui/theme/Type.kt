package com.davidchura.sistema1228.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.davidchura.sistema1228.R

val Urbanist = FontFamily(
    Font(R.font.urbanist_regular, FontWeight.Normal),
    Font(R.font.urbanist_bold, FontWeight.Bold),
    Font(R.font.urbanist_black, FontWeight.Black),
    Font(R.font.urbanist_extralight, FontWeight.ExtraLight)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    displayLarge = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Normal,
        fontSize = 64.sp,
        lineHeight = 70.sp,
        letterSpacing = 3.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
    )


    /* Other default text styles to override
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