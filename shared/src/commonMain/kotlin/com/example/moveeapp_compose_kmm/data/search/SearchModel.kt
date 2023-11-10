package com.example.moveeapp_compose_kmm.data.search

import com.example.moveeapp_compose_kmm.domain.MediaType
import com.example.moveeapp_compose_kmm.domain.search.SearchItem
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

        fun toDomain() = SearchItem(
            name = getMediaName(),
            imagePath = getImagePath(),
            mediaType = getMediaType(),
            id = id ?: 0
        )

        private fun getMediaName(): String =
            when (this.mediaType) {
                "movie" -> this.originalTitle ?: ""
                else -> this.name ?: ""
            }

        private fun getImagePath(): String =
            when (this.mediaType) {
                "person" -> this.profilePath ?: ""
                else -> this.posterPath ?: ""
            }

        private fun getMediaType(): MediaType? =
            when (this.mediaType) {
                MediaType.MOVIE.mediaType -> {
                    MediaType.MOVIE
                }

                MediaType.TV.mediaType -> {
                    MediaType.TV
                }

                MediaType.PERSON.mediaType -> {
                    MediaType.PERSON
                }
                else -> null
            }
    }
}
