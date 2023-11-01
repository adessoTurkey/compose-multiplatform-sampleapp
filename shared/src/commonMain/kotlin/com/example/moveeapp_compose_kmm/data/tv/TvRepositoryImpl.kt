package com.example.moveeapp_compose_kmm.data.tv

import com.example.moveeapp_compose_kmm.domain.tv.TvRepository
import com.example.moveeapp_compose_kmm.utils.resultOf
import kotlinx.coroutines.flow.flow

class TvRepositoryImpl(
    private val service: TvService
) : TvRepository {

    override fun getPopularTv() = flow {
        emit(
            resultOf {
                service.popularTv().tvSeries.map { it.toDomain() }
            }
        )
    }

    override fun getTopRatedTv() = flow {
        emit(
            resultOf {
                service.topRatedTv().tvSeries.map { it.toDomain() }
            }
        )
    }

    override fun getTvDetail(tvId: Int) = flow {
        emit(
            resultOf {
                service.tvDetail(tvId).toDomain()
            }
        )
    }

    override fun getTvCredits(tvId: Int) = flow {
        emit(
            resultOf {
                service.tvCredit(tvId).cast.map { it.toDomain() }
            }
        )
    }
}