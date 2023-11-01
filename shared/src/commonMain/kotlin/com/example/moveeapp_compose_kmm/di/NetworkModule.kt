package com.example.moveeapp_compose_kmm.di

import com.example.moveeapp_compose_kmm.data.account.AccountService
import com.example.moveeapp_compose_kmm.data.account.AccountServiceImpl
import com.example.moveeapp_compose_kmm.data.movie.MovieService
import com.example.moveeapp_compose_kmm.data.movie.MovieServiceImpl
import com.example.moveeapp_compose_kmm.data.remote.ApiImpl
import com.example.moveeapp_compose_kmm.data.remote.ApiInterface
import com.example.moveeapp_compose_kmm.data.remote.RatingService
import com.example.moveeapp_compose_kmm.data.remote.RatingServiceImpl
import com.example.moveeapp_compose_kmm.data.tv.TvService
import com.example.moveeapp_compose_kmm.data.tv.TvServiceImpl
import com.example.moveeapp_compose_kmm.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {
            defaultRequest {
                url {
                    takeFrom(Constants.BASE_URL)
                    parameters.append("api_key", Constants.API_KEY)

                }
            }
            expectSuccess = true
            install(HttpTimeout) {
                val timeout = 30000L
                connectTimeoutMillis = timeout
                requestTimeoutMillis = timeout
                socketTimeoutMillis = timeout
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    single<ApiInterface> { ApiImpl(get()) }
    single<RatingService> { RatingServiceImpl(get()) }
    single<AccountService> { AccountServiceImpl(get()) }
    single<MovieService> { MovieServiceImpl(get()) }
    single<TvService> { TvServiceImpl(get()) }
}
