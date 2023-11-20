package com.example.moveeapp_compose_kmm.core

import android.content.Context
import androidx.startup.Initializer
import java.lang.ref.WeakReference

object AppContext {
    private var _weakContext: WeakReference<Context?> = WeakReference(null)
    val context: Context? get() = _weakContext.get()

    fun setContext(context: Context) {
        _weakContext = WeakReference(context)
    }
}

@Suppress("UNUSED")
class ContextInitializer : Initializer<AppContext> {
    override fun create(context: Context): AppContext {
        AppContext.setContext(context.applicationContext)
        return AppContext
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}