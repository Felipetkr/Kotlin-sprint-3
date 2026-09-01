package com.fiap.latteconnect.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LatteLightColorScheme = lightColorScheme(
    primary = LatteBlueDark,
    onPrimary = LatteWhite,
    secondary = LatteAmber,
    onSecondary = LatteTextPrimary,
    tertiary = LatteBlueLight,
    background = LatteOffWhite,
    onBackground = LatteTextPrimary,
    surface = LatteWhite,
    onSurface = LatteTextPrimary,
    surfaceVariant = LatteBlueSoft,
    onSurfaceVariant = LatteTextSecondary,
    outline = LatteCardBorder
)

private val LatteDarkColorScheme = darkColorScheme(
    primary = LatteBlueLight,
    onPrimary = LatteBlueDarker,
    secondary = LatteAmber,
    onSecondary = LatteTextPrimary,
    tertiary = LatteBlueDark,
    background = LatteBlueDarker,
    onBackground = LatteOffWhite,
    surface = LatteBlueDark,
    onSurface = LatteOffWhite,
    surfaceVariant = LatteBlueDark,
    onSurfaceVariant = LatteBlueSoft,
    outline = LatteCardBorder
)

@Composable
fun LatteConnectTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) LatteDarkColorScheme else LatteLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = LatteTypography,
        content = content
    )
}
