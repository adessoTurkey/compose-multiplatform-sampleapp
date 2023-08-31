package com.example.moveeapp_compose_kmm.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp


@Composable
fun ChipItem(
    modifier: Modifier = Modifier,
    string: String,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.primaryContainer,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    shape: Shape = MaterialTheme.shapes.large

) {
    AssistChip(
        onClick = {},
        modifier = modifier.height(25.dp),
        label = { Text(text = string, color = textColor) },
        colors = AssistChipDefaults.assistChipColors(backgroundColor),
        shape = shape,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon
    )
}