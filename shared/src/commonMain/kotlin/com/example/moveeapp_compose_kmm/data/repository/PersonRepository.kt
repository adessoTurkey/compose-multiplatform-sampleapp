package com.example.moveeapp_compose_kmm.data.repository

import com.example.moveeapp_compose_kmm.data.remote.ApiInterface
import com.example.moveeapp_compose_kmm.utils.resultOf
import kotlinx.coroutines.flow.flow

class PersonRepository(private val api: ApiInterface) {

    fun getPersonDetail(personId: Int) = flow {
        emit(
            resultOf {
                api.personDetail(personId)
            }
        )
    }

    fun getPersonCredits(personId: Int) = flow {
        emit(
            resultOf {
                api.personCredit(personId)
            }
        )
    }
}