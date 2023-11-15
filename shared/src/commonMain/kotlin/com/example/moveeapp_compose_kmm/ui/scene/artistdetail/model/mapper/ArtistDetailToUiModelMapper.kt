package com.example.moveeapp_compose_kmm.ui.scene.artistdetail.model.mapper

import com.example.moveeapp_compose_kmm.domain.artist.ArtistCredit
import com.example.moveeapp_compose_kmm.domain.artist.ArtistDetail
import com.example.moveeapp_compose_kmm.ui.scene.artistdetail.model.ArtistDetailUiModel

class ArtistDetailToUiModelMapper {

    fun map(from: ArtistDetail, credit: List<ArtistCredit>): ArtistDetailUiModel {
        with(from) {
            return ArtistDetailUiModel(
                name = name,
                biography = biography,
                birthday = birthday,
                placeOfBirth = placeOfBirth,
                profilePath = profilePath,
                credit = credit
            )
        }
    }
}
