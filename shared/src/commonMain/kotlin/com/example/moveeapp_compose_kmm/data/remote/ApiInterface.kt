package com.example.moveeapp_compose_kmm.data.remote

import com.example.moveeapp_compose_kmm.data.remote.model.person.PersonCreditsModel
import com.example.moveeapp_compose_kmm.data.remote.model.person.PersonDetailModel

interface ApiInterface {

    //Person
    suspend fun personDetail(personId: Int): PersonDetailModel

    suspend fun personCredit(personId: Int): PersonCreditsModel

}
