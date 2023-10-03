package com.example.moveeapp_compose_kmm.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.moveeapp_compose_kmm.MR
import dev.icerock.moko.resources.compose.stringResource
import kotlin.math.ceil

private val rateButtonSize = 40.dp

private val spacerOffsetTarget = 16.dp
private val starItemStartPadding = 16.dp
private val dividerWidth = 1.dp
private val starItemSize = 40.dp
private val spaceBeforeRateButtons = spacerOffsetTarget + dividerWidth + starItemStartPadding

private const val starButtonNumber = 5
private const val rateFactor = 2

@Composable
fun RateButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    transition: Transition<Boolean>,
    value: State<Int?>,
    onValueChange: (Int) -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                ).size(rateButtonSize),
                onClick = {
                    onClick.invoke()
                }
            ) {
                Icon(
                    modifier = Modifier.size(17.dp),
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primaryContainer
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.animateContentSize()) {
                TextItem(
                    text = stringResource(MR.strings.rate),
                    fontSize = 15.sp
                )

                if (value.value != null) {
                    TextItem(
                        text = " (${value.value})",
                        fontSize = 15.sp
                    )
                }
            }
        }

        RateButtonContent(
            modifier = Modifier.padding(start = rateButtonSize),
            transition = transition,
            value = value,
            onValueChange = onValueChange
        )
    }
}

@Composable
private fun RateButtonContent(
    modifier: Modifier = Modifier,
    transition: Transition<Boolean>,
    value: State<Int?>,
    onValueChange: (Int) -> Unit
) {
    if (transition.lastTransitionedValue()) {
        val alpha by transition.animateFloat(transitionSpec = {
            if (true isTransitioningTo false) {
                tween(1000)
            } else {
                tween(250)
            }
        }) { visible ->
            if (visible) 1f else 0f
        }

        val spacerOffset by transition.animateDp(transitionSpec = {
            tween(1000)
        }) { visible ->
            if (visible) spacerOffsetTarget else 0.dp
        }

        var previousDragRating by remember { mutableStateOf<Int?>(null) }
        Box(modifier = modifier.fillMaxWidth()
            .alpha(alpha).pointerInput(Unit) {
                detectDragGestures(onDrag = { change, dragAmount ->
                    val x = change.position.x
                    val rating = getRatingByOffset(
                        xOffsetDp = x.toDp(),
                        spaceBeforeRateButtons = spaceBeforeRateButtons,
                        rateButtonWidth = starItemSize,
                        itemStartPadding = starItemStartPadding,
                        starButtonNumber = starButtonNumber
                    )

                    if (previousDragRating != rating) {
                        onValueChange.invoke(rating)
                    }

                    previousDragRating = rating
                })
            }.pointerInput(Unit) {
                detectTapGestures(onTap = { change ->
                    val x = change.x
                    val rating = getRatingByOffset(
                        xOffsetDp = x.toDp(),
                        spaceBeforeRateButtons = spaceBeforeRateButtons,
                        rateButtonWidth = starItemSize,
                        itemStartPadding = starItemStartPadding,
                        starButtonNumber = starButtonNumber
                    )

                    onValueChange.invoke(rating)
                })
            },
            contentAlignment = Alignment.CenterStart
        ) {
            Spacer(
                modifier = Modifier.height(rateButtonSize)
                    .offset(x = spacerOffset)
                    .width(dividerWidth)
                    .background(color = Color.Gray)
            )

            for (starNumber in 1..starButtonNumber) {
                val itemOffset by transition.animateDp(transitionSpec = {
                    tween(1000)
                }) { visible ->
                    if (visible) {
                        val starOffset = (starNumber - 1) * (starItemSize + starItemStartPadding)
                        starOffset + spaceBeforeRateButtons
                    } else {
                        0.dp
                    }
                }

                PartialStar(
                    modifier = Modifier.offset(x = itemOffset).size(starItemSize),
                    fraction = getFraction(value = value.value, starNumber = starNumber),
                    itemSize = starItemSize,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
private fun PartialStar(
    modifier: Modifier = Modifier,
    fraction: Float,
    itemSize: Dp,
    tint: Color
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Icon(
            modifier = Modifier.size(itemSize),
            imageVector = Icons.Outlined.StarOutline,
            contentDescription = null,
            tint = tint
        )

        Box {
            Icon(
                modifier = Modifier.size(itemSize).drawWithContent {
                    clipRect(right = size.width / 2f) {
                        this@drawWithContent.drawContent()
                    }
                },
                imageVector = if (fraction < 0.5f) {
                    Icons.Outlined.StarOutline
                } else {
                    Icons.Default.Star
                },
                contentDescription = null,
                tint = tint
            )

            Icon(
                modifier = Modifier.size(itemSize).drawWithContent {
                    clipRect(left = size.width / 2f) {
                        this@drawWithContent.drawContent()
                    }
                },
                imageVector = if (fraction <= 0.5) {
                    Icons.Outlined.StarOutline
                } else {
                    Icons.Default.Star
                },
                contentDescription = null,
                tint = tint
            )
        }
    }
}

private fun getFraction(value: Int?, starNumber: Int): Float {
    if (value == null) return 0f

    if (value == starNumber * 2 - 1) return 0.5f

    if (value <= starNumber * 2 - 2) return 0f

    if (value >= starNumber * 2) return 1f

    return 1f
}

fun Transition<Boolean>.lastTransitionedValue(): Boolean {
    return when {
        targetState -> true
        !isRunning && !currentState -> false
        else -> true
    }
}

private fun getRatingByOffset(
    xOffsetDp: Dp,
    spaceBeforeRateButtons: Dp,
    rateButtonWidth: Dp,
    itemStartPadding: Dp,
    starButtonNumber: Int
): Int {
    val xOffsetAdjustedToRatingArea = xOffsetDp - spaceBeforeRateButtons

    if (xOffsetAdjustedToRatingArea < 0.dp) return 0

    val rateButtonsWidth = getRateButtonsWidth(
        rateButtonWidth = rateButtonWidth,
        itemStartPadding = itemStartPadding,
        starButtonNumber = starButtonNumber
    )

    val rating = (xOffsetAdjustedToRatingArea / rateButtonsWidth) * starButtonNumber

    return (rating * rateFactor).roundUpToInt()
}

private fun getRateButtonsWidth(
    rateButtonWidth: Dp,
    itemStartPadding: Dp,
    starButtonNumber: Int
): Dp {
    val rateButtonsSpacesSum =
        (starButtonNumber - 1) * itemStartPadding //First padding shouldn't be calculated.
    val rateButtonsWidthsSum = starButtonNumber * rateButtonWidth
    return rateButtonsSpacesSum + rateButtonsWidthsSum

}

private fun Float.roundUpToInt(): Int {
    return ceil(this).toInt()
}
