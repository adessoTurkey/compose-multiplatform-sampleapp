package com.example.moveeapp_compose_kmm.android

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.moveeapp_compose_kmm.MainView
import moe.tlaster.precompose.lifecycle.setContent
import moe.tlaster.precompose.lifecycle.PreComposeActivity

class MainActivity : PreComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainView()
        }
    }
}

