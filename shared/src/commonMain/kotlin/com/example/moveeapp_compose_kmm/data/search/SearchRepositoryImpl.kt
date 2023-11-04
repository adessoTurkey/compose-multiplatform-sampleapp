package com.example.moveeapp_compose_kmm.data.search

import com.example.moveeapp_compose_kmm.domain.search.SearchRepository
import com.example.moveeapp_compose_kmm.utils.resultOf
import kotlinx.coroutines.flow.flow

class SearchRepositoryImpl(private val service: SearchService) : SearchRepository {

    override suspend fun getSearch(query: String) = flow {
        emit(
            resultOf {
                service.search(query).results.map { it.toDomain() }
            }
        )
    }
}
