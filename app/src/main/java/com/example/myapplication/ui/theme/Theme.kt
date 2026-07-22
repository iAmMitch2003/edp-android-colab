package com.example.myapplication.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Light Theme Colors
private val SkyBlue = Color(0xFF5DA9FF)
private val BabyBlue = Color(0xFFDCEEFF)
private val LightBlue = Color(0xFFAED9FF)
private val IceBlue = Color(0xFFF5FAFF)
private val TextBlue = Color(0xFF23405A)

// Dark Theme Colors
private val DeepBlue = Color(0xFF4A90E2)

private val DarkText = Color(0xFFF1F5F9)

private val LightColorScheme = lightColorScheme(
    primary = SkyBlue,
    onPrimary = Color.White,
    primaryContainer = BabyBlue,
    secondary = LightBlue,
    background = IceBlue,
    surface = Color.White,
    onSurface = TextBlue,
    onSurfaceVariant = Color(0xFF5F738C)
)

private val DarkColorScheme = darkColorScheme(
    primary = DeepBlue,
    onPrimary = Color.White,
    primaryContainer = Color(0xFF274C77),
    secondary = ActiveGreen,
    background = DarkBackground,
    surface = DarkSurface,
    onSurface = DarkText,
    onSurfaceVariant = Color(0xFFC7D2FE)
)

// Rounded shapes
private val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(12.dp),
    small = RoundedCornerShape(18.dp),
    medium = RoundedCornerShape(24.dp),
    large = RoundedCornerShape(30.dp),
    extraLarge = RoundedCornerShape(40.dp)
)

// Background gradient
val LocalBackgroundGradient = staticCompositionLocalOf {
    Brush.verticalGradient(
        colors = listOf(
            Color(0xFFF5FAFF),
            Color(0xFFDCEEFF),
            Color(0xFFC7E6FF)
        )
    )
}

@Composable
fun ProfileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val gradient = if (darkTheme) {
        Brush.verticalGradient(
            colors = listOf(
                Color(0xFF1E3A8A),
                Color(0xFF1E40AF),
                Color(0xFF0F172A)
            )
        )
    } else {
        Brush.verticalGradient(
            colors = listOf(
                Color(0xFFF5FAFF),
                Color(0xFFDCEEFF),
                Color(0xFFAED9FF)
            )
        )
    }

    CompositionLocalProvider(LocalBackgroundGradient provides gradient) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            shapes = AppShapes,
            content = content
        )
    }
}
