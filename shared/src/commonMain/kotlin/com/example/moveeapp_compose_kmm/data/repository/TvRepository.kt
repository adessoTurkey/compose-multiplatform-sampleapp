package com.example.moveeapp_compose_kmm.data.repository

import com.example.moveeapp_compose_kmm.data.remote.ApiInterface
import com.example.moveeapp_compose_kmm.utils.resultOf
import kotlinx.coroutines.flow.flow

class TvRepository(private val api: ApiInterface) {

    fun getPopularTv() = flow {
        emit(
            resultOf {
                api.popularTv()
            }
        )
    }

    fun getTopRatedTv() = flow {
        emit(
            resultOf {
                api.topRatedTv()
            }
        )
    }

    fun getTvDetail(tvId: Int) = flow {
        emit(
            resultOf {
                api.tvDetail(tvId)
            }
        )
    }

    fun getTvCredit(tvId: Int) = flow {
        emit(
            resultOf {
                api.tvCredit(tvId)
            }
        )
    }
}