package com.example.moveeapp_compose_kmm.core

import platform.Foundation.NSMapTable
import platform.Foundation.NSMapTableStrongMemory
import platform.Foundation.NSMapTableWeakMemory

actual class WeakMap<T, R> actual constructor() {
    private val weakHashMap = NSMapTable(
        keyOptions = NSMapTableStrongMemory,
        valueOptions = NSMapTableWeakMemory,
        capacity = 3u
    )

    @Suppress("UNCHECKED_CAST")
    actual fun put(key: T, value: R): R {
        weakHashMap.setObject(anObject = value, forKey = key)
        return weakHashMap.objectForKey(key) as R
    }

    @Suppress("UNCHECKED_CAST")
    actual fun get(key: T): R? {
        return weakHashMap.objectForKey(key) as? R
    }

    @Suppress("UNCHECKED_CAST")
    actual fun getAllNonNull(): List<Pair<T, R>> {
        return weakHashMap.dictionaryRepresentation().entries
            .filter {
                it.key != null && it.value != null
            }.map {
                (it.key to it.value) as Pair<T, R>
            }
    }
}