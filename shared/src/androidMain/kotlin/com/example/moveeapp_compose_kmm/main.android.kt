package com.example.moveeapp_compose_kmm

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.moveeapp_compose_kmm.di.init
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication

@Composable
fun MainView() {
    val context = LocalContext.current
    KoinApplication(application = {
        androidContext(context.applicationContext)
        init()
    }) {
        App()
    }
}
