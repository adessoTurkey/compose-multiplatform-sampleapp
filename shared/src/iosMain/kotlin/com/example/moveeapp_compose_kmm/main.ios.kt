package com.example.moveeapp_compose_kmm

import androidx.compose.ui.window.ComposeUIViewController
import com.example.moveeapp_compose_kmm.di.init
import com.example.moveeapp_compose_kmm.utils.Action
import com.example.moveeapp_compose_kmm.utils.createStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
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

val store = CoroutineScope(SupervisorJob()).createStore()
