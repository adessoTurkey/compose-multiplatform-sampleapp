package com.example.moveeapp_compose_kmm.data.search

import com.example.moveeapp_compose_kmm.MR
import com.example.moveeapp_compose_kmm.domain.search.Search
import com.example.moveeapp_compose_kmm.utils.Constants
import dev.icerock.moko.resources.ImageResource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchModel(
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<Result>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int
) {

    @Serializable
    data class Result(
        @SerialName("adult") val adult: Boolean? = null,
        @SerialName("backdrop_path") val backdropPath: String? = null,
        @SerialName("first_air_date") val firstAirDate: String? = null,
        @SerialName("genre_ids") val genreIds: List<Int>? = null,
        @SerialName("id") val id: Int? = null,
        @SerialName("media_type") val mediaType: String? = null,
        @SerialName("name") val name: String? = null,
        @SerialName("origin_country") val originCountry: List<String>? = null,
        @SerialName("original_language") val originalLanguage: String? = null,
        @SerialName("original_name") val originalName: String? = null,
        @SerialName("original_title") val originalTitle: String? = null,
        @SerialName("overview") val overview: String? = null,
        @SerialName("popularity") val popularity: Double? = null,
        @SerialName("poster_path") val posterPath: String? = null,
        @SerialName("profile_path") val profilePath: String? = null,
        @SerialName("release_date") val releaseDate: String? = null,
        @SerialName("title") val title: String? = null,
        @SerialName("video") val video: Boolean? = null,
        @SerialName("vote_average") val voteAverage: Double? = null,
        @SerialName("vote_count") val voteCount: Int? = null
    ) {

        fun toDomain() = Search(
            name = getDisplayName(),
            imagePath = getImagePath(),
            type = getIconType(),
            mediaType = getNameType(),
            id = id ?: 0
        )

        private fun getNameType(): String =
            when (this.mediaType) {
                Constants.MOVIE -> {
                    "Movie"
                }

                Constants.TV -> {
                    "TV Series"
                }

                Constants.PERSON -> {
                    "Person"
                }

                else -> ""
            }

        private fun getIconType(): ImageResource =
            when (this.mediaType) {
                "movie" -> {
                    MR.images.ic_search_movie
                }

                "tv" -> {
                    MR.images.ic_search_tv
                }

                else -> MR.images.ic_search_actor
            }

        private fun getDisplayName(): String =
            when (this.mediaType) {
                "movie" -> this.originalTitle ?: ""
                else -> this.name ?: ""
            }

        private fun getImagePath(): String =
            when (this.mediaType) {
                "person" -> this.profilePath ?: ""
                else -> this.posterPath ?: ""
            }
    }
}