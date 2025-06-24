package com.example.moveeapp_compose_kmm.nav

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.SeekableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.PredictiveBackHandler
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.stack.StackEvent
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.ScreenTransitionContent
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PredictiveBackTransition(
    navigator: Navigator,
    modifier: Modifier = Modifier,
    enterTransition: AnimatedContentTransitionScope<Screen>.(SwipeEdge) -> EnterTransition = { fadeIn() },
    exitTransition: AnimatedContentTransitionScope<Screen>.(SwipeEdge) -> ExitTransition = { fadeOut() },
    popEnterTransition: AnimatedContentTransitionScope<Screen>.(SwipeEdge) -> EnterTransition = enterTransition,
    popExitTransition: AnimatedContentTransitionScope<Screen>.(SwipeEdge) -> ExitTransition = exitTransition,
    sizeTransform: (AnimatedContentTransitionScope<Screen>.() -> SizeTransform?)? = null,
    flingAnimationSpec: () -> AnimationSpec<Float> = { spring(stiffness = Spring.StiffnessLow) },
    content: ScreenTransitionContent = { it.Content() }
) {
    val scope = rememberCoroutineScope()
    var animationJob by remember { mutableStateOf<Pair<Job, AnimationType>?>(null) }

    var progress by remember { mutableFloatStateOf(0f) }
    var swipeEdge by remember { mutableStateOf(SwipeEdge.Unknown) }
    var isPredictiveBack by remember { mutableStateOf(false) }
    val transition = if (sizeTransform != null) {
        // Size transform seems broken atm so this is a more hacky way to keep the size transition working.
        // this method breaks when the user spams back gesture or quick navigation action in general.
        val transitionState = remember { SeekableTransitionState(navigator.lastItem) }
        LaunchedEffect(progress) {
            val previousEntry = navigator.items.getOrNull(navigator.size - 2)
            if (previousEntry != null) transitionState.seekTo(
                targetState = previousEntry,
                fraction = progress
            )
        }
        LaunchedEffect(navigator) {
            snapshotFlow { navigator.lastItem }
                .collect {
                    if (animationJob?.second == AnimationType.Cancel) {
                        animationJob?.first?.cancel()
                    }
                    transitionState.animateTo(it)
                }
        }
        rememberTransition(transitionState = transitionState)
    } else {
        if (isPredictiveBack) {
            val transitionState = remember { SeekableTransitionState(navigator.lastItem) }
            LaunchedEffect(progress) {
                val previousEntry = navigator.items.getOrNull(navigator.size - 2)
                if (previousEntry != null) transitionState.seekTo(
                    targetState = previousEntry,
                    fraction = progress
                )
            }
            rememberTransition(transitionState = transitionState)
        } else {
            updateTransition(targetState = navigator.lastItem)
        }
    }

    PredictiveBackHandler(enabled = navigator.canPop) { backEvent ->
        if (animationJob?.second == AnimationType.Cancel) {
            animationJob?.first?.cancel()
        }
        try {
            backEvent
                .dropWhile { animationJob != null }
                .collect {
                    swipeEdge = when (it.swipeEdge) {
                        /* BackEventCompat.EDGE_LEFT */0 -> SwipeEdge.Left
                        /* BackEventCompat.EDGE_RIGHT */ 1 -> SwipeEdge.Right
                        else -> SwipeEdge.Unknown
                    }
                    progress = it.progress
                    isPredictiveBack = true
                }
            animationJob = scope.launch {
                try {
                    if (isPredictiveBack) {
                        animate(
                            initialValue = progress,
                            targetValue = 1f,
                            animationSpec = flingAnimationSpec(),
                            block = { i, _ -> },
                        )
                    }
                    navigator.pop()
                } catch (e: CancellationException) {
                    // Cancelled
                    progress = 0f
                } finally {
                    isPredictiveBack = false
                    swipeEdge = SwipeEdge.Unknown
                    animationJob = null
                }
            } to AnimationType.Pop
        } catch (e: CancellationException) {
            animationJob = scope.launch {
                try {
                    if (isPredictiveBack) {
                        animate(
                            initialValue = progress,
                            targetValue = 0f,
                            animationSpec = flingAnimationSpec(),
                            block = { i, _ -> progress = i },
                        )
                    }
                } catch (e: CancellationException) {
                    // Cancelled
                    progress = 1f
                } finally {
                    isPredictiveBack = false
                    swipeEdge = SwipeEdge.Unknown
                    animationJob = null
                }
            } to AnimationType.Cancel
        }
    }

    transition.AnimatedContent(
        modifier = modifier,
        transitionSpec = {
            val pop = navigator.lastEvent == StackEvent.Pop || isPredictiveBack
            ContentTransform(
                targetContentEnter = if (pop) popEnterTransition(swipeEdge) else enterTransition(
                    swipeEdge
                ),
                initialContentExit = if (pop) popExitTransition(swipeEdge) else exitTransition(
                    swipeEdge
                ),
                targetContentZIndex = if (pop) 0f else 1f,
                sizeTransform = sizeTransform?.invoke(this),
            )
        },
        contentKey = { it.key },
    ) {
        navigator.saveableState("transition", it) {
            content(it)
        }
    }
}

enum class SwipeEdge {
    Unknown,
    Left,
    Right,
}

private enum class AnimationType {
    Pop, Cancel
}
