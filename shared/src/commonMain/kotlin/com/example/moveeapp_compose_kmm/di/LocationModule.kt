package com.example.moveeapp_compose_kmm.di

import com.example.moveeapp_compose_kmm.data.remote.NominatimImpl
import com.example.moveeapp_compose_kmm.data.remote.NominatimInterface
import io.ktor.client.HttpClient
import org.koin.dsl.module

val locationModule = module {
    single<NominatimInterface> {
        val client = get<HttpClient>()
        NominatimImpl(client)
    }
}