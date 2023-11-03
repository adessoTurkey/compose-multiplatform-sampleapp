package com.example.moveeapp_compose_kmm.di

import com.example.moveeapp_compose_kmm.data.account.AccountRepositoryImpl
import com.example.moveeapp_compose_kmm.data.movie.MovieRepositoryImpl
import com.example.moveeapp_compose_kmm.data.repository.LoginRepository
import com.example.moveeapp_compose_kmm.data.repository.PersonRepository
import com.example.moveeapp_compose_kmm.data.repository.RatingRepositoryImpl
import com.example.moveeapp_compose_kmm.data.repository.SearchRepository
import com.example.moveeapp_compose_kmm.data.tv.TvRepositoryImpl
import com.example.moveeapp_compose_kmm.domain.account.AccountRepository
import com.example.moveeapp_compose_kmm.domain.movie.MovieRepository
import com.example.moveeapp_compose_kmm.domain.rating.RatingRepository
import com.example.moveeapp_compose_kmm.domain.tv.TvRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    single { LoginRepository(get(), get()) }
    single<TvRepository> { TvRepositoryImpl(get()) }
    single { SearchRepository(get()) }
    single { PersonRepository(get()) }
    single<AccountRepository> { AccountRepositoryImpl(get(), get(), get()) }
    single<RatingRepository> { RatingRepositoryImpl(get(), get()) }
}
