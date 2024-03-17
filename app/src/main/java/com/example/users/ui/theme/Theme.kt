package com.example.users.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColors = darkColors(
    primary = Purple80,
    secondary = PurpleGrey80,

)



private val LightColors= lightColors(
    primary = Color(0xFFFFFFFFF),
    primaryVariant = Color(0xff6534FF),
    onPrimary = Color(0xff050510),
    secondary = Color(0xff97979B),
    secondaryVariant = Color(0xff55555C),
    surface = Color(0xffF7F7F8),
    onSurface = Color(0xffC3C3C6),
    error = Color(0xffF44336)
)

@Composable
fun UsersTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if(darkTheme) DarkColors else LightColors,
        typography = Typography,
        content = content
    )
}