package com.example.moveeapp_compose_kmm.di

import com.example.moveeapp_compose_kmm.core.IosLocationRepository
import com.example.moveeapp_compose_kmm.data.MultiplatformSettingsProvider
import com.example.moveeapp_compose_kmm.domain.account.SessionSettings
import com.example.moveeapp_compose_kmm.domain.location.LocationRepository
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.KeychainSettings
import org.koin.dsl.module

@OptIn(ExperimentalSettingsImplementation::class)
actual val settingsModule = module {
    single {
        SessionSettings(
            MultiplatformSettingsProvider(KeychainSettings(NameSessionSettings))
        )
    }
}
actual val locationModule = module {
    single<LocationRepository> { IosLocationRepository() }
}
