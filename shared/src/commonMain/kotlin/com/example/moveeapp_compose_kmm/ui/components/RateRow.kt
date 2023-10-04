package com.example.moveeapp_compose_kmm.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * @param hidableContent The content that is made hidden when rating stars are visible.
 */
@Composable
fun RateRow(
    modifier: Modifier = Modifier,
    ratingValue: State<Int?>,
    onRatingValueChange: (rate: Int) -> Unit,
    hidableContent: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        var ratingContentVisibility by remember { mutableStateOf(false) }
        val transition = updateTransition(ratingContentVisibility)

        RateButton(
            onClick = { ratingContentVisibility = !ratingContentVisibility },
            transition = transition,
            value = ratingValue,
            onValueChange = onRatingValueChange
        )

        AnimatedVisibility(
            modifier = Modifier.padding(start = 32.dp),
            visible = !transition.lastTransitionedValue(),
        ) {
            hidableContent.invoke(this@Row)
        }
    }
}