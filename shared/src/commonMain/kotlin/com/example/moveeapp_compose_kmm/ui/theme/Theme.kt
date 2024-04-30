package com.example.moveeapp_compose_kmm.ui.theme


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.unit.dp
import movee.shared.generated.resources.Res
import movee.shared.generated.resources.sfpro_bold
import movee.shared.generated.resources.sfpro_medium
import movee.shared.generated.resources.sfpro_regular
import org.jetbrains.compose.resources.Font

private val shapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
)

private val colors = lightColorScheme().copy(
    primary = Color(0xFF003dff), //blue
    secondary = Color(0xFF08090a), //black
    primaryContainer = Color(0xFFFFFFFF), //white
    secondaryContainer = Color(0xFFf4f4f4), //lightGray
    background = Color(0xFFf4f4f4)
)

val Color.isLight: Boolean
    get() {
        return luminance() >= 0.8
    }

object Fonts {
    lateinit var bold: FontFamily
    lateinit var medium: FontFamily
    lateinit var regular: FontFamily
}

@Composable
fun AppTheme(
    content: @Composable() () -> Unit
) {
    Fonts.bold = Font(Res.font.sfpro_bold).toFontFamily()
    Fonts.medium = Font(Res.font.sfpro_medium).toFontFamily()
    Fonts.regular = Font(Res.font.sfpro_regular).toFontFamily()

    MaterialTheme(
        colorScheme = colors,
        shapes = shapes,
        content = content
    )
}
