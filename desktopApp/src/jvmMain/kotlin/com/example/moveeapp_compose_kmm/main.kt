package com.example.moveeapp_compose_kmm

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.example.moveeapp_compose_kmm.di.init
import org.koin.compose.KoinApplication
import java.awt.Dimension

fun main() = application {
    Window(
        title = "Movee",
        state = rememberWindowState(width = 360.dp, height = 720.dp),
        onCloseRequest = ::exitApplication,
    ) {
        window.minimumSize = Dimension(300, 600)
        KoinApplication(application = {
            init()
        }) {
            App()
        }
    }
}
