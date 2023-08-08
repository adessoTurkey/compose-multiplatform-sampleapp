package com.example.moveeapp_compose_kmm.android

import android.app.Application
import com.example.moveeapp_compose_kmm.di.commonAppModule
import com.example.moveeapp_compose_kmm.di.networkModule
import com.example.moveeapp_compose_kmm.di.repositoryModule
import com.example.moveeapp_compose_kmm.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MoveeApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            //androidContext(this@MoveeApplication)
            //androidLogger()
            modules(networkModule, repositoryModule, viewModelModule)
        }
    }
}
