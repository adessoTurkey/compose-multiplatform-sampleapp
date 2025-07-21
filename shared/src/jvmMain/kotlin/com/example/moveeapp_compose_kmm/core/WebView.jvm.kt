package com.example.moveeapp_compose_kmm.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import javafx.application.Platform
import javafx.embed.swing.JFXPanel
import javafx.scene.Scene
import javafx.scene.web.WebView
import javax.swing.JPanel

@Composable
actual fun WebView(modifier: Modifier, link: String) {
    val jPanel: JPanel = remember { JPanel() }
    val jfxPanel = JFXPanel()

    SwingPanel(
        factory = {
            jfxPanel.apply { buildWebView(link) }
            jPanel.add(jfxPanel)
        },
        modifier = modifier,
    )

    DisposableEffect(link) { onDispose { jPanel.remove(jfxPanel) } }
}

@Suppress("SetJavaScriptEnabled")
private fun JFXPanel.buildWebView(url: String) {
    Platform.runLater {
        val webView = WebView()
        val webEngine = webView.engine

        webEngine.userAgent =
            "Mozilla/5.0 (Linux; Android 11; SAMSUNG SM-G973U) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/14.2 Chrome/87.0.4280.141 Mobile Safari/537.36"

        webEngine.isJavaScriptEnabled = true

        webEngine.load(url)
        val scene = Scene(webView)
        setScene(scene)
    }
}
