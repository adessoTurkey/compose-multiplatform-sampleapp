package com.example.moveeapp_compose_kmm.ui.scene.search.model.mapper

import com.example.moveeapp_compose_kmm.MR
import com.example.moveeapp_compose_kmm.domain.MediaType
import com.example.moveeapp_compose_kmm.domain.search.SearchItem
import com.example.moveeapp_compose_kmm.ui.scene.search.model.SearchUiModel
import dev.icerock.moko.resources.ImageResource

class SearchItemToUiModelMapper {
    fun map(from: SearchItem): SearchUiModel {
        with(from) {
            return SearchUiModel(
                name = name,
                imagePath = imagePath,
                iconType = getIconType(from),
                mediaType = mediaType?.title ?: "",
                id = id
            )
        }
    }

    private fun getIconType(searchItem: SearchItem): ImageResource =
        when (searchItem.mediaType) {
            MediaType.MOVIE -> {
                MR.images.ic_search_movie
            }

            MediaType.TV -> {
                MR.images.ic_search_tv
            }

            else -> MR.images.ic_search_actor
        }
}
