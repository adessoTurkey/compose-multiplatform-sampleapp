package com.example.moveeapp_compose_kmm.core

import androidx.compose.runtime.Composable
import com.example.moveeapp_compose_kmm.Holder
import platform.UIKit.UIActivityViewController

@Composable
actual fun Share(text: String) {
    val controller = UIActivityViewController(listOf(text), null)

    Holder.viewController?.presentViewController(controller, true, null)
}