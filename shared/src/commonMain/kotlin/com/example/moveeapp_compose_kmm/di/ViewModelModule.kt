package com.example.moveeapp_compose_kmm.di

import com.example.moveeapp_compose_kmm.ui.scene.actordetail.ActorDetailViewModel
import com.example.moveeapp_compose_kmm.ui.scene.moviescreen.MovieViewModel
import com.example.moveeapp_compose_kmm.ui.scene.loginscreen.LoginViewModel
import com.example.moveeapp_compose_kmm.ui.scene.moviedetailscreen.MovieDetailViewModel
import com.example.moveeapp_compose_kmm.ui.scene.searchscreen.SearchViewModel
import com.example.moveeapp_compose_kmm.ui.scene.splashscreen.SplashViewModel
import com.example.moveeapp_compose_kmm.ui.scene.tvdetailscreen.TvDetailViewModel
import com.example.moveeapp_compose_kmm.ui.scene.tvscreen.TvViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { MovieViewModel(get()) }
    factory { LoginViewModel(get()) }
    factory { MovieDetailViewModel(get()) }
    factory { SplashViewModel(get()) }
    factory { TvViewModel(get()) }
    factory { TvDetailViewModel(get()) }
    factory { SearchViewModel(get()) }
    factory { ActorDetailViewModel(get()) }
}