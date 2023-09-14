package com.example.moveeapp_compose_kmm.data.uimodel

data class ActorDetailUiModel (
val name: String = "",
val biography: String = "",
val birthday: String = "",
val placeOfBirth: String = "",
val profilePath: String = "",
val credit: List<ActorCreditUiModel> = listOf()
)

data class ActorCreditUiModel (
    val imagePath: String? = null,
    val name: String? = null,
    val voteAverage: Double? = null,
    val releaseDate: String? = null,
    val id: Int? = null,
    val mediaType: String? = null
)
