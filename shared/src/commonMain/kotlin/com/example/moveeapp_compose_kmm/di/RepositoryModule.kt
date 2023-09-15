package com.example.moveeapp_compose_kmm.di

import com.example.moveeapp_compose_kmm.data.repository.LoginRepository
import com.example.moveeapp_compose_kmm.data.repository.MovieRepository
import com.example.moveeapp_compose_kmm.data.repository.PersonRepository
import com.example.moveeapp_compose_kmm.data.repository.SearchRepository
import com.example.moveeapp_compose_kmm.data.repository.TvRepository
import org.koin.dsl.module

val repositoryModule = module{
    single { MovieRepository(get()) }
    single { LoginRepository(get(), get()) }
    single { TvRepository(get()) }
    single { SearchRepository(get()) }
    single { PersonRepository(get()) }
}