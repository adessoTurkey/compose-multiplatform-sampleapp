package com.example.moveeapp_compose_kmm.di

import com.example.moveeapp_compose_kmm.data.repository.LoginRepository
import com.example.moveeapp_compose_kmm.data.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module{
    single { MovieRepository(get()) }
    single { LoginRepository(get()) }
}