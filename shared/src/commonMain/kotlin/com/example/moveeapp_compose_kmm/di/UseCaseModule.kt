package com.example.moveeapp_compose_kmm.di

import com.example.moveeapp_compose_kmm.domain.account.GetAccountDetailUseCase
import com.example.moveeapp_compose_kmm.domain.favorite.AddFavoriteUseCase
import com.example.moveeapp_compose_kmm.domain.favorite.GetMovieStateUseCase
import com.example.moveeapp_compose_kmm.domain.favorite.GetTvStateUseCase
import com.example.moveeapp_compose_kmm.domain.account.LogoutUseCase
import com.example.moveeapp_compose_kmm.domain.rating.RateMovieUseCase
import com.example.moveeapp_compose_kmm.domain.rating.RateTvShowUseCase
import com.example.moveeapp_compose_kmm.domain.rating.RemoveMovieRatingUseCase
import com.example.moveeapp_compose_kmm.domain.rating.RemoveTvShowRatingUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { AddFavoriteUseCase(get()) }
    factory { GetMovieStateUseCase(get()) }
    factory { GetAccountDetailUseCase(get()) }
    factory { GetTvStateUseCase(get()) }
    factory { LogoutUseCase(get(), get()) }
    factory { RateMovieUseCase(get()) }
    factory { RateTvShowUseCase(get()) }
    factory { RemoveMovieRatingUseCase(get()) }
    factory { RemoveTvShowRatingUseCase(get()) }
    factory { CinemaSearchUseCase(get()) }
}
