package com.example.profileapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = BluePrimary,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD6E8FF),
    onPrimaryContainer = Color(0xFF001B3D),
    secondary = BlueSecondary,
    onSecondary = Color.White,
    background = Color(0xFFE3F2FD), // More noticeable light blue background
    onBackground = Color(0xFF0D47A1),
    surface = Color.White,
    onSurface = Color(0xFF0D47A1),
    surfaceVariant = Color(0xFFBBDEFB),
    onSurfaceVariant = Color(0xFF1976D2)
)

private val DarkColors = darkColorScheme(
    primary = BlueSecondary,
    onPrimary = Color(0xFF003354),
    primaryContainer = BluePrimary,
    onPrimaryContainer = Color(0xFFD6E8FF),
    background = BlueBackground,
    onBackground = BlueText,
    surface = BlueSurface,
    onSurface = BlueText,
    surfaceVariant = Color(0xFF334E68),
    onSurfaceVariant = BlueText
)

@Composable
fun ProfileAppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content
    )
}