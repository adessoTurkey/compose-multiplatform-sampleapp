package com.example.moveeapp_compose_kmm.domain.usecase.accountusecase

import com.example.moveeapp_compose_kmm.data.remote.model.account.favorite.AddFavoriteRequestModel
import com.example.moveeapp_compose_kmm.data.repository.AccountRepository
import com.example.moveeapp_compose_kmm.domain.model.IsFavorite
import com.example.moveeapp_compose_kmm.utils.Constants

class AddFavoriteUseCase(private val repository: AccountRepository) {

    suspend fun execute(
        accountId: Int,
        mediaId: Int,
        mediaType: String,
        isFavorite: Boolean
    ): Result<IsFavorite> {
        val result = repository.addFavorite(
            accountId = accountId,
            AddFavoriteRequestModel(
                favorite = isFavorite,
                mediaId = mediaId,
                mediaType = mediaType
            )
        )
        return if (result.isSuccess) {
            when (mediaType) {
                Constants.MOVIE -> {
                    repository.getMovieIsFavorite(mediaId)
                }

                else -> {
                    repository.getTvIsFavorite(mediaId)
                }
            }
        } else {
            Result.failure(Throwable(message = "Hata!"))
        }
    }
}