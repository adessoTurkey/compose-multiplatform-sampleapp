package com.example.moveeapp_compose_kmm.core

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
inline fun <T> T?.ifNotNull(block: (T) -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    if (this != null) {
        block(this)
    }
}

@OptIn(ExperimentalContracts::class)
inline fun <T1, T2> ifNotNull(t1: T1?, t2: T2?, block: (T1, T2) -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    if (t1 != null && t2 != null) {
        block(t1, t2)
    }
}

@OptIn(ExperimentalContracts::class)
inline fun <T1, T2, T3> ifNotNull(t1: T1?, t2: T2?, t3: T3?, block: (T1, T2, T3) -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    if (t1 != null && t2 != null && t3 != null) {
        block(t1, t2, t3)
    }
}
