package com.example.moveeapp_compose_kmm.di

import com.example.moveeapp_compose_kmm.ui.scene.homescreen.HomeViewModel
import com.example.moveeapp_compose_kmm.ui.scene.loginscreen.LoginViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { HomeViewModel(get()) }
    factory { LoginViewModel(get()) }
}