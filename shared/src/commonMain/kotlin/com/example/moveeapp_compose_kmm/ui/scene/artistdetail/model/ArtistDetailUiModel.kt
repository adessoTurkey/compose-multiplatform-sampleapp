package com.example.moveeapp_compose_kmm.ui.scene.artistdetail.model

import com.example.moveeapp_compose_kmm.domain.artist.ArtistCredit

data class ArtistDetailUiModel(
    val name: String = "",
    val biography: String = "",
    val birthday: String = "",
    val placeOfBirth: String = "",
    val profilePath: String = "",
    val credit: List<ArtistCredit> = listOf()
)