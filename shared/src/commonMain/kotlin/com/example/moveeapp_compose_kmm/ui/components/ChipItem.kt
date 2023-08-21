package com.example.moveeapp_compose_kmm.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ChipItem(
    modifier: Modifier = Modifier,
    string: String,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.secondary,
    shape: CornerBasedShape = MaterialTheme.shapes.large

) {
    Surface(shape = shape, color = backgroundColor) {
        Text(
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 6.dp),
            text = string,
            color = textColor,
            fontSize = 14.sp
        )
    }
}