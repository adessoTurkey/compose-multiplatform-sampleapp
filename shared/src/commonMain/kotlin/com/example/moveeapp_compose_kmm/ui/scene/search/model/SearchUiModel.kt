package com.example.moveeapp_compose_kmm.ui.scene.search.model

import org.jetbrains.compose.resources.DrawableResource

class SearchUiModel(
    val name: String = "",
    val imagePath: String = "",
    val iconType: DrawableResource? = null,
    val mediaType: String = "",
    val id: Int = 0
)
