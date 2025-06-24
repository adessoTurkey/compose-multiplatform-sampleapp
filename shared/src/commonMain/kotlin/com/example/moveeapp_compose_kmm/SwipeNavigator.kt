package com.example.moveeapp_compose_kmm

import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.navigator.Navigator

@Composable
fun SwipeNavigator(navigator: Navigator) {

    val currentSceneEntry = navigator.lastItem
    val prevSceneEntry = remember(navigator.items) { navigator.items.getOrNull(navigator.size - 2) }

    var prevWasSwiped by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(currentSceneEntry) {
        prevWasSwiped = false
    }

    val dismissState = key(currentSceneEntry) {
        rememberSwipeToDismissBoxState()
    }

    LaunchedEffect(dismissState.currentValue) {
        if (dismissState.currentValue == SwipeToDismissBoxValue.StartToEnd) {
            prevWasSwiped = true
            navigator.pop()
        }
    }

    val showPrev by remember(dismissState) {
        derivedStateOf {
            dismissState.progress > 0f
        }
    }
}
