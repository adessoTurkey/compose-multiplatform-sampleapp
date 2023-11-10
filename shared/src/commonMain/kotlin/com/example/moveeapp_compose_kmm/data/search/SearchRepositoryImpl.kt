package com.example.moveeapp_compose_kmm.data.search

import com.example.moveeapp_compose_kmm.domain.search.SearchItem
import com.example.moveeapp_compose_kmm.domain.search.SearchRepository
import com.example.moveeapp_compose_kmm.utils.resultOf

class SearchRepositoryImpl(private val service: SearchService) : SearchRepository {

    override suspend fun getSearch(query: String): Result<List<SearchItem>> {
        return resultOf {
            service.search(query).results.map { it.toDomain() }
        }
    }
}
