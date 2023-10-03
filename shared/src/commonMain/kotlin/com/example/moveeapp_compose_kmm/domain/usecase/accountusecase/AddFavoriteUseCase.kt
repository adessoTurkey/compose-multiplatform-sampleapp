package com.example.moveeapp_compose_kmm.domain.usecase.accountusecase

import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.AddFavoriteRequestModel
import com.example.moveeapp_compose_kmm.data.repository.AccountRepository

class AddFavoriteUseCase(private val repository: AccountRepository) {

    suspend fun execute(
        accountId: Int,
        mediaId: Int,
        mediaType: String,
        isFavorite: Boolean
    ): Result<Unit> {
        val result = repository.addFavorite(
            accountId = accountId,
            AddFavoriteRequestModel(
                favorite = isFavorite,
                mediaId = mediaId,
                mediaType = mediaType
            )
        )
        return if (result.isSuccess && result.getOrNull()?.success == true) {
            Result.success(Unit)
        } else {
            Result.failure(Throwable())
        }
    }
}