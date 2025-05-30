package com.example.moveeapp_compose_kmm.di

import com.example.moveeapp_compose_kmm.core.AndroidLocationRepository
import com.example.moveeapp_compose_kmm.data.MultiplatformSettingsProvider
import com.example.moveeapp_compose_kmm.domain.account.SessionSettings
import com.example.moveeapp_compose_kmm.domain.location.LocationRepository
import com.russhwolf.settings.SharedPreferencesSettings
import dev.spght.encryptedprefs.EncryptedSharedPreferences
import dev.spght.encryptedprefs.MasterKey
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val settingsModule = module {
    single {
        val masterKey = MasterKey(androidContext(), keyScheme = MasterKey.KeyScheme.AES256_GCM)
        val prefs = EncryptedSharedPreferences.create(
            androidContext(),
            NameSessionSettings,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        SessionSettings(
            MultiplatformSettingsProvider(SharedPreferencesSettings(prefs))
        )
    }
}

actual val locationModule = module {
    factory<LocationRepository> { AndroidLocationRepository(context = androidContext()) }
}
