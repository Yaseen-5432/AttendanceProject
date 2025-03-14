package com.example.attendancesystem.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.attendancesystem.R

val Rubikfont = FontFamily(
    Font(R.font.rubik_black,FontWeight.Black),
    Font(R.font.rubik_bold,FontWeight.Bold),
    Font(R.font.rubik_extrabold,FontWeight.ExtraBold),
    Font(R.font.rubik_light,FontWeight.Light),
    Font(R.font.rubik_medium,FontWeight.Medium),
    Font(R.font.rubik_regular,FontWeight.Normal),
    Font(R.font.rubik_semibold,FontWeight.SemiBold),



)
// Set of Material typography styles to start with
val Typography = Typography(
    headlineMedium = TextStyle(
        fontFamily = Rubikfont,
       fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = Rubikfont,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = Rubikfont,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = Rubikfont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = Rubikfont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
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


