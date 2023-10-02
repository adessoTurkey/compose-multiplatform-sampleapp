package com.example.moveeapp_compose_kmm

import androidx.compose.ui.window.ComposeUIViewController
import com.example.moveeapp_compose_kmm.utils.Action

fun MainViewController() = ComposeUIViewController{ App() }

fun onBackGesture() {
    store.send(Action.OnBackPressed)
}