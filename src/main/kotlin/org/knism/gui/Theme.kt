package org.knism.gui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val DarkThemeColors = Colors(
    primary = Color(255, 125, 125),
    primaryVariant = Color(255, 200, 125),
    secondary = Color(125, 255, 125),
    secondaryVariant = Color(150, 255, 125),
    background = Color(125, 125, 125),
    surface = Color(0, 0, 0),
    error = Color(0, 0, 0),
    onPrimary = Color(0, 0, 0),
    onSecondary = Color(0, 0, 0),
    onBackground = Color(0, 0, 0),
    onSurface = Color(0, 0, 0),
    onError = Color(0, 0, 0),
    isLight = false
)

private val LightThemeColors = Colors(
    primary = Color(255, 125, 125),
    primaryVariant = Color(255, 200, 125),
    secondary = Color(125, 255, 125),
    secondaryVariant = Color(150, 255, 125),
    background = Color(200, 250, 200),
    surface = Color(255, 220, 200),
    error = Color(255, 50, 50),
    onPrimary = Color(255, 255, 255),
    onSecondary = Color(200, 200, 200),
    onBackground = Color(50, 50, 0),
    onSurface = Color(50, 25, 25),
    onError = Color(255, 255, 255),
    isLight = true
)

@Composable
fun CustomTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        content = content
    )
}