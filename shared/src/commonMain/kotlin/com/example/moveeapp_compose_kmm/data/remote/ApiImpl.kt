package com.example.moveeapp_compose_kmm.data.remote

import com.example.moveeapp_compose_kmm.data.remote.model.person.PersonCreditsModel
import com.example.moveeapp_compose_kmm.data.remote.model.person.PersonDetailModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiImpl(private val client: HttpClient) : ApiInterface {


    //Person
    override suspend fun personDetail(personId: Int): PersonDetailModel {
        return client.get("person/$personId").body()
    }

    override suspend fun personCredit(personId: Int): PersonCreditsModel {
        return client.get("person/$personId/combined_credits").body()
    }

    companion object {
        const val LOGOUT = "authentication/session"
    }
}


