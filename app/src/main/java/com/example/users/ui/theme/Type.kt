package com.example.users.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.users.R

val inter = FontFamily(
    Font(R.font.inter_medium, FontWeight.W500)

)
val Typography = Typography(

    h1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    h6 = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.W500,
        lineHeight = 20.sp,
        fontSize = 16.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.W500,
        lineHeight = 18.sp,
        fontSize = 14.sp,
        color = Color(0XFF97979B)
    ),
    caption = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.W400,
        lineHeight = 16.sp,
        fontSize = 13.sp,
        color = Color(0XFF55555C)
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