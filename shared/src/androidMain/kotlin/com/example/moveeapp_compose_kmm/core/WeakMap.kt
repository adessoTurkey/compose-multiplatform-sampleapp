package com.example.moveeapp_compose_kmm.core

import java.util.*

actual class WeakMap<T, R> actual constructor() {
    private val weakHashMap = WeakHashMap<T, R>()

    actual fun put(key: T, value: R): R {
        weakHashMap[key] = value
        return weakHashMap[key]!!
    }

    actual fun get(key: T): R? {
        return weakHashMap[key]
    }

    actual fun getAllNonNull(): List<Pair<T, R>> {
        return weakHashMap.entries
            .filter {
                it.key != null && it.value != null
            }.map {
                it.key to it.value
            }
    }
}