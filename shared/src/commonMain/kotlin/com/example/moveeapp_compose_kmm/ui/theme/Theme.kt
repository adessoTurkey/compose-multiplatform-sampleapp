package com.example.moveeapp_compose_kmm.ui.theme


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val shapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
)

private val colors = lightColorScheme().copy(
    primary = Color(0xFF003dff),
    secondary = Color(0xFF08090a),
    primaryContainer = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFf4f4f4),
)

@Composable
fun AppTheme(
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colorScheme = colors,
        shapes = shapes,
        content = content
    )
}