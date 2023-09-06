package com.example.moveeapp_compose_kmm.di

import com.example.moveeapp_compose_kmm.ui.scene.moviescreen.MovieViewModel
import com.example.moveeapp_compose_kmm.ui.scene.loginscreen.LoginViewModel
import com.example.moveeapp_compose_kmm.ui.scene.moviedetailscreen.MovieDetailViewModel
import com.example.moveeapp_compose_kmm.ui.scene.splashscreen.SplashViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { MovieViewModel(get()) }
    factory { LoginViewModel(get()) }
    factory { MovieDetailViewModel(get()) }
    factory { SplashViewModel(get()) }
}