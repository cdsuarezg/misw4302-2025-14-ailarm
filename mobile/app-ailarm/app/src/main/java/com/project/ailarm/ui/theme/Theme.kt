package com.project.ailarm.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = White,

    primaryContainer = BackgroundColor,
    onPrimaryContainer = TextColor,

    secondary = SecondaryColor,
    onSecondary = White,

    tertiaryContainer = SecondaryColor,

    background = BackgroundColor,
    onBackground = BackgroundColor,

    surface = BackgroundColor,
    onSurface = TextColor,
)

@Composable
fun AilarmTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content,
    )
}
