package com.example.moveeapp_compose_kmm

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.LogLevel
import io.github.aakira.napier.Napier

object Logger {

    fun init() {
        Napier.base(DebugAntilog())
    }

    inline fun v(message: String, throwable: Throwable? = null, tag: String? = null) {
        Napier.log(LogLevel.VERBOSE, tag, throwable, message)
    }

    inline fun i(message: String, throwable: Throwable? = null, tag: String? = null) {
        Napier.log(LogLevel.INFO, tag, throwable, message)
    }

    inline fun d(message: String, throwable: Throwable? = null, tag: String? = null) {
        Napier.log(LogLevel.DEBUG, tag, throwable, message)
    }

    inline fun w(message: String, throwable: Throwable? = null, tag: String? = null) {
        Napier.log(LogLevel.WARNING, tag, throwable, message)
    }

    inline fun e(message: String, throwable: Throwable? = null, tag: String? = null) {
        Napier.log(LogLevel.ERROR, tag, throwable, message)
    }

    inline fun wtf(message: String, throwable: Throwable? = null, tag: String? = null) {
        Napier.log(LogLevel.ASSERT, tag, throwable, message)
    }
}

inline fun log(
    priority: LogLevel = LogLevel.DEBUG,
    throwable: Throwable? = null,
    tag: String? = null,
    message: String,
) {
    Napier.log(priority, tag, throwable, message)
}

inline fun log(
    priority: LogLevel = LogLevel.DEBUG,
    throwable: Throwable? = null,
    tag: String? = null,
    message: () -> String,
) {
    Napier.log(priority, tag, throwable, message())
}
