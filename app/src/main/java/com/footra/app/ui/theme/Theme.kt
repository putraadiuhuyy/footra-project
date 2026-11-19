package com.footra.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalConfiguration

private val FootraColorScheme = lightColorScheme(
    primary = FootraColors.Black,
    onPrimary = FootraColors.White,
    background = FootraColors.White,
    surface = FootraColors.White,
    onBackground = FootraColors.Black,
    onSurface = FootraColors.Black
)

@Composable
fun FootraTheme(content: @Composable () -> Unit) {
    // 1 Figma px = screenWidthDp / 1080 dp  (frame width in the design is 1080)
    val screenWidthDp = LocalConfiguration.current.screenWidthDp
    CompositionLocalProvider(LocalDesignScale provides screenWidthDp / 1080f) {
        MaterialTheme(colorScheme = FootraColorScheme, content = content)
    }
}
