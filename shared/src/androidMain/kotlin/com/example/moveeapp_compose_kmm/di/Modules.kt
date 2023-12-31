package com.example.moveeapp_compose_kmm.di

import com.example.moveeapp_compose_kmm.domain.account.SessionSettings
import com.example.moveeapp_compose_kmm.data.KVaultSettingsProvider
import com.example.moveeapp_compose_kmm.core.AndroidLocationRepository
import com.example.moveeapp_compose_kmm.domain.location.LocationRepository

import com.liftric.kvault.KVault
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val settingsModule = module {
    single {
        SessionSettings(
            settingsProvider = KVaultSettingsProvider(
                KVault(androidContext(), NameSessionSettings)
            )
        )
    }
}

actual val locationModule = module {
    factory <LocationRepository> { AndroidLocationRepository(context = androidContext()) }
}
