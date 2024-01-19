package com.example.moveeapp_compose_kmm.core

import com.example.moveeapp_compose_kmm.Holder
import platform.UIKit.UIActivityViewController

actual fun share(context: PlatformContext, text: String) {
    val controller = UIActivityViewController(listOf(text), null)

    Holder.viewController?.presentViewController(controller, true, null)
}