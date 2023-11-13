package com.example.moveeapp_compose_kmm.data.favorite

interface FavoriteService {

    suspend fun addFavorite(
        accountId: Int,
        addFavoriteRequestModel: AddFavoriteRequestModel,
        sessionId: String
    ): AddFavoriteResponseModel

    suspend fun getFavoriteMovie(accountId: Int, sessionId: String): FavoriteMovieModel

    suspend fun getFavoriteTv(accountId: Int, sessionId: String): FavoriteTvModel
}
