package io.webt.webthingsdashboard.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    surface = LightBlue,
    onSurface = White,
    primary = DarkBlue,
    onPrimary = DarkWhite
    /* TODO */
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    surface = LightBlue,
    onSurface = White,
    primary = DarkBlue,
    onPrimary = White,
    background = LightBlue,
    onBackground = White,
    secondary = DarkBlue,
    onSecondary = DarkWhite

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun WebthingsDashboardTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        LightColorPalette//TODO
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}