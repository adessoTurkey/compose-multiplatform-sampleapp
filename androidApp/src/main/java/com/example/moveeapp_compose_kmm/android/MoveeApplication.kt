package com.example.moveeapp_compose_kmm.android

import android.app.Application
import com.example.moveeapp_compose_kmm.di.init
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoveeApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MoveeApplication)
            //androidLogger()
            init()
        }
    }
}
