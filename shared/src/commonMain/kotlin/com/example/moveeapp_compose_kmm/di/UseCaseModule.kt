package com.example.moveeapp_compose_kmm.di

import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.AddFavoriteUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.GetAccountDetailUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.GetMovieStateUseCase
import com.example.moveeapp_compose_kmm.domain.usecase.accountusecase.GetTvStateUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { AddFavoriteUseCase(get()) }
    factory { GetMovieStateUseCase(get()) }
    factory { GetAccountDetailUseCase(get()) }
    factory { GetTvStateUseCase(get()) }
}