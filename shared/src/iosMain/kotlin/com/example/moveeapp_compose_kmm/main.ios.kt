package com.example.moveeapp_compose_kmm

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import com.example.moveeapp_compose_kmm.utils.Action

fun MainViewController(): UIViewController {
    val uiViewController = ComposeUIViewController { App() }
    Holder.viewController = uiViewController
    return uiViewController
}

internal object Holder {
    var viewController: UIViewController? = null
}

fun onBackGesture() {
    store.send(Action.OnBackPressed)
}