package com.example.moveeapp_compose_kmm.di

import com.example.moveeapp_compose_kmm.domain.AddFavoriteUseCase
import com.example.moveeapp_compose_kmm.domain.GetAccountDetailUseCase
import com.example.moveeapp_compose_kmm.domain.GetMovieStateUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { AddFavoriteUseCase(get()) }
    factory { GetMovieStateUseCase(get()) }
    factory { GetAccountDetailUseCase(get()) }
}