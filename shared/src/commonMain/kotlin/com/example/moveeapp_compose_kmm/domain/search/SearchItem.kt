package com.example.moveeapp_compose_kmm.domain.search

import com.example.moveeapp_compose_kmm.domain.MediaType

data class SearchItem(
    val name: String = "",
    val imagePath: String = "",
    val mediaType: MediaType? = null,
    val id: Int = 0
)
