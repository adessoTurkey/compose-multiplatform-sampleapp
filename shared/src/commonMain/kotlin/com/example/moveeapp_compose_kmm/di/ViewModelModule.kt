package com.example.moveeapp_compose_kmm.di

import com.example.moveeapp_compose_kmm.ui.scene.account.AccountDetailViewModel
import com.example.moveeapp_compose_kmm.ui.scene.account.favoritescreen.FavoriteViewModel
import com.example.moveeapp_compose_kmm.ui.scene.artistdetail.ArtistDetailViewModel
import com.example.moveeapp_compose_kmm.ui.scene.login.LoginViewModel
import com.example.moveeapp_compose_kmm.ui.scene.map.MapViewModel
import com.example.moveeapp_compose_kmm.ui.scene.main.MainViewModel
import com.example.moveeapp_compose_kmm.ui.scene.moviedetail.MovieDetailViewModel
import com.example.moveeapp_compose_kmm.ui.scene.movie.MovieViewModel
import com.example.moveeapp_compose_kmm.ui.scene.search.SearchViewModel
import com.example.moveeapp_compose_kmm.ui.scene.splashscreen.SplashViewModel
import com.example.moveeapp_compose_kmm.ui.scene.tvdetail.TvDetailViewModel
import com.example.moveeapp_compose_kmm.ui.scene.tv.TvViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { MainViewModel() }
    factory { MovieViewModel(get()) }
    factory { LoginViewModel(get(), get(), get()) }
    factory { MovieDetailViewModel(get(), get(), get(), get(), get(), get()) }
    factory { AccountDetailViewModel(get(), get()) }
    factory { SplashViewModel(get()) }
    factory { TvViewModel(get()) }
    factory { TvDetailViewModel(get(), get(), get(), get(), get(), get()) }
    factory { SearchViewModel(get()) }
    factory { ArtistDetailViewModel(get()) }
    factory { FavoriteViewModel(get(), get()) }
    factory { MapViewModel(get(), get()) }
}
