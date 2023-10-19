package com.example.moveeapp_compose_kmm

import androidx.compose.ui.window.ComposeUIViewController
import com.example.moveeapp_compose_kmm.di.init
import com.example.moveeapp_compose_kmm.utils.Action
import org.koin.compose.KoinApplication
import platform.UIKit.UIViewController

fun mainViewController(): UIViewController {
    val uiViewController = ComposeUIViewController {
        KoinApplication(application = {
            init()
        }) {
            App()
        }
    }
    Holder.viewController = uiViewController
    return uiViewController
}

internal object Holder {
    var viewController: UIViewController? = null
}

fun onBackGesture() {
    store.send(Action.OnBackPressed)
}
