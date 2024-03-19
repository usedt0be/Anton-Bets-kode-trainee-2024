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
    Font(R.font.inter_medium, FontWeight.W500),
    Font(R.font.inter_semibold, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold),
    Font(R.font.inter_regular, FontWeight.W400)
)
val Typography = Typography(

    h2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W700,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.5.sp,
        color = Color(0XFF050510)
    ),
    h3 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W400,
        fontSize = 17.sp,
        lineHeight = 22.sp,
        color = Color(0XFF97979B)
    ),
    h4  = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 20.sp,
        fontSize = 15.sp,
        color = Color(0XFF050510)
    ),
    h5  = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 18.sp,
        fontSize = 14.sp,
        color = Color(0XFF6534FF)
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
    subtitle2 = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.W500,
        lineHeight = 20.sp,
        fontSize = 15.sp,
        color = Color(0XFF97979B)
    ),

    caption = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.W400,
        lineHeight = 16.sp,
        fontSize = 13.sp,
        color = Color(0XFF55555C)
    )

)