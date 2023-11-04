package com.example.moveeapp_compose_kmm.domain.search

import dev.icerock.moko.resources.ImageResource

data class Search(
    val name: String = "",
    val imagePath: String = "",
    val type: ImageResource? = null,
    val mediaType: String = "",
    val id: Int = 0
)
