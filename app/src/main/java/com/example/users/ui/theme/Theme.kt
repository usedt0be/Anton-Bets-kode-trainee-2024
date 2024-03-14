package com.example.users.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColors = darkColors(
    primary = Purple80,
    secondary = PurpleGrey80,

)

private val LightColors= lightColors(
    primary = Purple40,
    secondary = PurpleGrey40,
)

@Composable
fun UsersTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if(darkTheme) DarkColors else LightColors,
        typography = Typography,
        content = content
    )
}