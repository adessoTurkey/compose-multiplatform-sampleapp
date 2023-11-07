package com.example.moveeapp_compose_kmm.domain.favorite

import com.example.moveeapp_compose_kmm.data.favorite.AddFavoriteRequestModel
import com.example.moveeapp_compose_kmm.data.favorite.AddFavoriteResponseModel

interface FavoriteRepository {

    suspend fun addFavorite(
        accountId: Int,
        addFavoriteRequestModel: AddFavoriteRequestModel
    ): Result<AddFavoriteResponseModel>

    suspend fun getFavoriteMovie(accountId: Int, sessionId: String): Result<List<FavoriteMovie>>

    suspend fun getFavoriteTv(accountId: Int, sessionId: String): Result<List<FavoriteTv>>
}
