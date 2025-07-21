package com.example.moveeapp_compose_kmm.core

import com.example.moveeapp_compose_kmm.utils.Constants
import platform.Foundation.NSURL
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication

actual fun share(
    context: PlatformContext,
    title: String,
    description: String?,
    imageUrl: String?
) {
    val text = title + description?.let { "\n$it" }
    val items = mutableListOf<Any>(text)
    imageUrl?.let {
        NSURL(string = Constants.IMAGE_BASE + it)
    }?.also {
        items.add(it)
    }

    UIApplication.sharedApplication.keyWindow?.rootViewController?.presentViewController(
        UIActivityViewController(items, null), true, null
    )
}
