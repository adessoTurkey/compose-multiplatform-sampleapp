package com.example.moveeapp_compose_kmm.ui.scene.search.model.mapper

import com.example.moveeapp_compose_kmm.domain.MediaType
import com.example.moveeapp_compose_kmm.domain.search.SearchItem
import com.example.moveeapp_compose_kmm.ui.scene.search.model.SearchUiModel
import movee.shared.generated.resources.Res
import movee.shared.generated.resources.ic_search_actor
import movee.shared.generated.resources.ic_search_movie
import movee.shared.generated.resources.ic_search_tv
import org.jetbrains.compose.resources.DrawableResource

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

    private fun getIconType(searchItem: SearchItem): DrawableResource? =
        when (searchItem.mediaType) {
            MediaType.MOVIE -> {
                Res.drawable.ic_search_movie
            }

            MediaType.TV -> {
                Res.drawable.ic_search_tv
            }

            MediaType.PERSON -> {
                Res.drawable.ic_search_actor
            }

            else -> null
        }
}
