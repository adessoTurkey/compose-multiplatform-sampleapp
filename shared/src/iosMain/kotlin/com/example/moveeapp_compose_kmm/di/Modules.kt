package com.example.moveeapp_compose_kmm.di

import com.example.moveeapp_compose_kmm.core.IosLocationRepository
import com.example.moveeapp_compose_kmm.domain.account.SessionSettings
import com.example.moveeapp_compose_kmm.data.KVaultSettingsProvider
import com.example.moveeapp_compose_kmm.domain.location.LocationRepository
import com.liftric.kvault.KVault
import org.koin.dsl.module

actual val settingsModule = module {
    single {
        SessionSettings(
            KVaultSettingsProvider(KVault(NameSessionSettings))
        )
    }
}
actual val locationModule = module {
    single<LocationRepository> { IosLocationRepository() }
}
