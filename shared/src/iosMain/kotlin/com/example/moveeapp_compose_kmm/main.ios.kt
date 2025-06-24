package com.example.moveeapp_compose_kmm

import androidx.compose.ui.window.ComposeUIViewController
import com.example.moveeapp_compose_kmm.di.init
import org.koin.compose.KoinApplication
import platform.UIKit.UIViewController

fun mainViewController(): UIViewController {
    return ComposeUIViewController {
        KoinApplication(application = { init() }) {
            App()
        }
    }
}
