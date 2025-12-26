package com.vagiii.asdfnovel.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColors = lightColors(
    primary = androidx.compose.ui.graphics.Color(0xFF1E88E5),
    primaryVariant = androidx.compose.ui.graphics.Color(0xFF1565C0),
    secondary = androidx.compose.ui.graphics.Color(0xFF00ACC1)
)

@Composable
fun AsdfNovelTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColors,
        typography = androidx.compose.material.Typography(),
        shapes = androidx.compose.material.Shapes(),
        content = content
    )
}
