package com.example.moveeapp_compose_kmm.di

import com.example.moveeapp_compose_kmm.data.MultiplatformSettingsProvider
import com.example.moveeapp_compose_kmm.domain.account.SessionSettings
import com.example.moveeapp_compose_kmm.domain.location.DeviceLocation
import com.example.moveeapp_compose_kmm.domain.location.LocationRepository
import com.russhwolf.settings.PreferencesSettings
import org.koin.dsl.module
import java.util.prefs.Preferences

actual val settingsModule = module {
    single {
        SessionSettings(
            MultiplatformSettingsProvider(
                PreferencesSettings(Preferences.userRoot().node(NameSessionSettings))
            )
        )
    }
}

actual val locationModule = module {
    factory<LocationRepository> {
        object : LocationRepository {
            override suspend fun getCurrentLocation(): DeviceLocation {
                return DeviceLocation(0.0, 0.0)
            }
        }
    }
}


