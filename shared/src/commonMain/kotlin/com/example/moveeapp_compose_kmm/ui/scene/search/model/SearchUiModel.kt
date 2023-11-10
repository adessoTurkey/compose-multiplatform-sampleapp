package com.example.moveeapp_compose_kmm.ui.scene.search.model

import dev.icerock.moko.resources.ImageResource

class SearchUiModel(
    val name: String = "",
    val imagePath: String = "",
    val iconType: ImageResource? = null,
    val mediaType: String = "",
    val id: Int = 0
)