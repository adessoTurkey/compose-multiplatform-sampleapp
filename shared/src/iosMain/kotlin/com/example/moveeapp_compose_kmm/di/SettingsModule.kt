package com.example.moveeapp_compose_kmm.di

import com.example.moveeapp_compose_kmm.core.SessionSettings
import com.liftric.kvault.KVault
import org.koin.dsl.module

actual val settingsModule = module {
    single { SessionSettings(KVault("session-settings")) }
}