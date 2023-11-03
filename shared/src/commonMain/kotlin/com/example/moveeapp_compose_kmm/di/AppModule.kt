package com.example.moveeapp_compose_kmm.di

import org.koin.core.KoinApplication
import org.koin.core.module.Module

fun KoinApplication.init() {
    modules(networkModule, repositoryModule, viewModelModule, settingsModule, useCaseModule, locationModule)
}

expect val settingsModule: Module
expect val locationModule: Module

const val NameSessionSettings = "session-settings"
