package com.example.moveeapp_compose_kmm.data.tv

import com.example.moveeapp_compose_kmm.domain.account.SessionSettings
import com.example.moveeapp_compose_kmm.domain.favorite.TvAccountState
import com.example.moveeapp_compose_kmm.domain.artist.Credits
import com.example.moveeapp_compose_kmm.domain.tv.PopularTv
import com.example.moveeapp_compose_kmm.domain.tv.TopRatedTv
import com.example.moveeapp_compose_kmm.domain.tv.TvDetail
import com.example.moveeapp_compose_kmm.domain.tv.TvRepository
import com.example.moveeapp_compose_kmm.utils.resultOf

class TvRepositoryImpl(
    private val service: TvService,
    private val sessionSettings: SessionSettings
) : TvRepository {

    override suspend fun getPopularTv(): Result<List<PopularTv>> {
        return resultOf {
            service.popularTv().tvSeries.map { it.toDomain() }
        }
    }

    override suspend fun getTopRatedTv(): Result<List<TopRatedTv>> {
        return resultOf {
            service.topRatedTv().tvSeries.map { it.toDomain() }
        }
    }

    override suspend fun getTvDetail(tvId: Int): Result<TvDetail> {
        return resultOf {
            service.tvDetail(tvId).toDomain()
        }
    }

    override suspend fun getTvCredits(tvId: Int): Result<List<Credits>> {
        return resultOf {
            service.tvCredit(tvId).cast.map { it.toDomain() }
        }
    }

    override suspend fun getTvAccountState(tvId: Int): Result<TvAccountState> {
        return resultOf {
            val response = service.getTvState(
                sessionId = sessionSettings.getSessionId() ?: "", tvId
            )
            TvAccountState(response.favorite ?: false, response.rated?.value)
        }
    }
}
