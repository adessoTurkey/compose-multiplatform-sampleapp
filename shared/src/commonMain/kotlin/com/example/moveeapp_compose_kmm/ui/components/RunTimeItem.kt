package com.example.moveeapp_compose_kmm.ui.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RuntimeItem(
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.primary,
    runtime: String
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Default.Timer,
            contentDescription = null,
            modifier = Modifier.padding(end = 2.dp),
            tint = textColor
        )
        Text(
            text = "$runtime min",
            fontSize = 14.sp,
            color = textColor
        )
    }
}