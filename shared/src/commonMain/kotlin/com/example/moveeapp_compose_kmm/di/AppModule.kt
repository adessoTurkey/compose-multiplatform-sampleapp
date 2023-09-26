package com.example.moveeapp_compose_kmm.di

import org.koin.core.KoinApplication
import org.koin.core.module.Module

fun KoinApplication.init(){
    modules(networkModule, repositoryModule, viewModelModule, settingsModule, useCaseModule)
}

expect val settingsModule: Module