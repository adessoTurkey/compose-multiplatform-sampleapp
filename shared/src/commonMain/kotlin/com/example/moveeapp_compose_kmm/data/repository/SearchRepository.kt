package com.example.moveeapp_compose_kmm.data.repository

import com.example.moveeapp_compose_kmm.data.remote.ApiInterface
import com.example.moveeapp_compose_kmm.utils.resultOf
import kotlinx.coroutines.flow.flow

class SearchRepository(private val api: ApiInterface) {

    fun getSearch(query: String) = flow {
        emit(
            resultOf {
                api.search(query)
            }
        )
    }
}