package com.example.moveeapp_compose_kmm.domain.account.favorite

import com.example.moveeapp_compose_kmm.data.account.favorite.AddFavoriteRequestModel
import com.example.moveeapp_compose_kmm.domain.account.AccountRepository

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
