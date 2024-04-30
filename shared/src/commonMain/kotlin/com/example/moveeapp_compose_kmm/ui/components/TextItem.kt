package com.example.moveeapp_compose_kmm.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.moveeapp_compose_kmm.ui.theme.Fonts

@Composable
fun TextItem(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = MaterialTheme.colorScheme.secondary,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Clip,
    lineHeight: TextUnit = TextUnit.Unspecified,
    fontFamily: FontFamily = Fonts.regular
) {
    Text(
        text = text,
        modifier = modifier,
        color = textColor,
        fontSize = fontSize,
        fontWeight = fontWeight,
        maxLines = maxLines,
        overflow = overflow,
        lineHeight = lineHeight,
        fontFamily = fontFamily
    )
}
