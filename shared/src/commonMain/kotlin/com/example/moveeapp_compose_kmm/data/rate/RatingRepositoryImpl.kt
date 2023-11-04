package com.example.moveeapp_compose_kmm.data.rate

import com.example.moveeapp_compose_kmm.domain.account.SessionSettings
import com.example.moveeapp_compose_kmm.domain.rating.RatingRepository
import com.example.moveeapp_compose_kmm.utils.resultOf

class RatingRepositoryImpl(
    private val service: RatingService,
    private val sessionSettings: SessionSettings,
) : RatingRepository {

    override suspend fun rateMovie(rating: Double, movieId: Int): Result<Boolean> {
        val result = resultOf {
            service.rateMovie(
                rating = RateDto(value = rating),
                movieId = movieId,
                sessionId = sessionSettings.getSessionId()!!
            )
        }

        return result.map { it.success }
    }

    override suspend fun rateTvShow(rating: Double, movieId: Int): Result<Boolean> {
        val result = resultOf {
            service.rateTvShow(
                rating = RateDto(value = rating),
                tvShowId = movieId,
                sessionId = sessionSettings.getSessionId()!!
            )
        }

        return result.map { it.success }
    }

    override suspend fun removeMovieRating(movieId: Int): Result<Unit> {
        return resultOf {
            service.removeMovieRating(movieId, sessionSettings.getSessionId()!!)
        }
    }

    override suspend fun removeTvShowRating(tvShowId: Int): Result<Unit> {
        return resultOf {
            service.removeTvShowRating(tvShowId, sessionSettings.getSessionId()!!)
        }
    }
}
