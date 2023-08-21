package com.example.moveeapp_compose_kmm

import com.example.moveeapp_compose_kmm.di.init
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        init()
    }
}