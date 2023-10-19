package com.example.moveeapp_compose_kmm.android

import android.app.Application
import com.example.moveeapp_compose_kmm.Logger

class MoveeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Logger.init()
    }
}
