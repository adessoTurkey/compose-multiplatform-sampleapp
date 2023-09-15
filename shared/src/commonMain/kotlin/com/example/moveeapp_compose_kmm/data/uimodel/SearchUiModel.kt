package com.example.moveeapp_compose_kmm.data.uimodel

import dev.icerock.moko.resources.ImageResource

data class SearchUiModel(
    val name: String = "",
    val imagePath: String = "",
    val type: ImageResource? = null,
    val mediaType: String = "",
    val id: Int = 0
)