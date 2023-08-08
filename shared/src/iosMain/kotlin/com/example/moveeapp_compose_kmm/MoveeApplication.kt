package com.example.moveeapp_compose_kmm

import com.example.moveeapp_compose_kmm.di.networkModule
import com.example.moveeapp_compose_kmm.di.repositoryModule
import com.example.moveeapp_compose_kmm.di.viewModelModule
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(networkModule, repositoryModule, viewModelModule)
    }
}