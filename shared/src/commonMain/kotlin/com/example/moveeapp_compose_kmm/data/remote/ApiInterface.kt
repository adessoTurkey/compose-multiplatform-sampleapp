package com.example.moveeapp_compose_kmm.data.remote

import com.example.moveeapp_compose_kmm.data.remote.model.SearchModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.LoginRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.LoginResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.RequestTokenResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.SessionRequestModel
import com.example.moveeapp_compose_kmm.data.remote.model.login.SessionResponseModel
import com.example.moveeapp_compose_kmm.data.remote.model.person.PersonCreditsModel
import com.example.moveeapp_compose_kmm.data.remote.model.person.PersonDetailModel

interface ApiInterface {

    //Person
    suspend fun personDetail(personId: Int): PersonDetailModel

    suspend fun personCredit(personId: Int): PersonCreditsModel

}
