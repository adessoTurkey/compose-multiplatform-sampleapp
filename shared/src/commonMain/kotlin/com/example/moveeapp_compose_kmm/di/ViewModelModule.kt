package com.example.moveeapp_compose_kmm.di

import com.example.moveeapp_compose_kmm.ui.scene.account.AccountDetailViewModel
import com.example.moveeapp_compose_kmm.ui.scene.account.favoritescreen.FavoriteViewModel
import com.example.moveeapp_compose_kmm.ui.scene.actordetail.ActorDetailViewModel
import com.example.moveeapp_compose_kmm.ui.scene.loginscreen.LoginViewModel
import com.example.moveeapp_compose_kmm.ui.scene.moviedetailscreen.MovieDetailViewModel
import com.example.moveeapp_compose_kmm.ui.scene.moviescreen.MovieViewModel
import com.example.moveeapp_compose_kmm.ui.scene.searchscreen.SearchViewModel
import com.example.moveeapp_compose_kmm.ui.scene.splashscreen.SplashViewModel
import com.example.moveeapp_compose_kmm.ui.scene.tvdetailscreen.TvDetailViewModel
import com.example.moveeapp_compose_kmm.ui.scene.tvscreen.TvViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { MovieViewModel(get()) }
    factory { LoginViewModel(get(), get(), get()) }
    factory { MovieDetailViewModel(get(), get(), get(), get()) }
    factory { AccountDetailViewModel(get()) }
    factory { SplashViewModel(get()) }
    factory { TvViewModel(get()) }
    factory { TvDetailViewModel(get(), get(), get(), get()) }
    factory { SearchViewModel(get()) }
    factory { ActorDetailViewModel(get()) }
    factory { FavoriteViewModel(get(), get()) }
}