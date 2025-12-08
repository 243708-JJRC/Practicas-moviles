package com.jjuanrc.examen_u3.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFDC0A2D),
    onPrimary = Color.White,
    secondary = Color(0xFFFFCB05),
    background = Color(0xFFF5F5F5),
    surface = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFDC0A2D),
    onPrimary = Color.White,
    secondary = Color(0xFFFFCB05),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E)
)

@Composable
fun PokemonTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}