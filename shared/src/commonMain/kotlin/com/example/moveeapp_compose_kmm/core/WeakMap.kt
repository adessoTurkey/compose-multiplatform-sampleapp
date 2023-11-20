package com.example.moveeapp_compose_kmm.core

expect class WeakMap<T, R>() {
    fun put(key: T, value: R): R

    fun get(key: T): R?

    fun getAllNonNull(): List<Pair<T, R>>
}