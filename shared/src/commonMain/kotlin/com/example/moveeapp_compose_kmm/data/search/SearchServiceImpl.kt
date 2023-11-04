package com.example.moveeapp_compose_kmm.data.search

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SearchServiceImpl(private val client: HttpClient) : SearchService {
    override suspend fun search(query: String): SearchModel {
        return client.get(SEARCH) {
            url {
                parameters.append("query", query)
            }
        }.body()
    }

    companion object {
        const val SEARCH = "search/multi"
    }
}
