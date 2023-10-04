package com.example.moveeapp_compose_kmm.di

import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.AddFavoriteUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.GetAccountDetailUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.GetMovieStateUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.GetTvStateUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.LogoutUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.rating.RateMovieUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.rating.RateTvShowUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.rating.RemoveMovieRatingUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.rating.RemoveTvShowRatingUseCase
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
}