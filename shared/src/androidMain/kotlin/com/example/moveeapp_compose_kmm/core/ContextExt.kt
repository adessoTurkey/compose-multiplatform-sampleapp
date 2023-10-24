package com.example.moveeapp_compose_kmm.core

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

internal fun Context.getActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}
