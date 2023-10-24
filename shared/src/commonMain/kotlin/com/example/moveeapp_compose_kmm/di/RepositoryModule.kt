package com.example.moveeapp_compose_kmm.di

import com.example.moveeapp_compose_kmm.data.account.AccountRepositoryImpl
import com.example.moveeapp_compose_kmm.data.favorite.FavoriteRepositoryImpl
import com.example.moveeapp_compose_kmm.data.movie.MovieRepositoryImpl
import com.example.moveeapp_compose_kmm.data.rate.RatingRepositoryImpl
import com.example.moveeapp_compose_kmm.data.repository.PersonRepository
import com.example.moveeapp_compose_kmm.data.search.SearchRepositoryImpl
import com.example.moveeapp_compose_kmm.data.tv.TvRepositoryImpl
import com.example.moveeapp_compose_kmm.domain.account.AccountRepository
import com.example.moveeapp_compose_kmm.domain.favorite.FavoriteRepository
import com.example.moveeapp_compose_kmm.domain.movie.MovieRepository
import com.example.moveeapp_compose_kmm.domain.rating.RatingRepository
import com.example.moveeapp_compose_kmm.domain.search.SearchRepository
import com.example.moveeapp_compose_kmm.domain.tv.TvRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
    single<TvRepository> { TvRepositoryImpl(get(), get()) }
    single<SearchRepository> { SearchRepositoryImpl(get()) }
    single { PersonRepository(get()) }
    single<AccountRepository> { AccountRepositoryImpl(get(), get()) }
    single<RatingRepository> { RatingRepositoryImpl(get(), get()) }
    single<FavoriteRepository> { FavoriteRepositoryImpl(get(), get()) }
    single { MapRepository(get()) }
}
