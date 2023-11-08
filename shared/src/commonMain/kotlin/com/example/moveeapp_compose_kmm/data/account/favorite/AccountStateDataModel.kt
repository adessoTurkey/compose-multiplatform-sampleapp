package com.example.moveeapp_compose_kmm.data.account.favorite

data class AccountStateDataModel(
    val favorite: Boolean?,
    val id: Int?,
    val isRated: Boolean?,
    val userRate: Double?,
    val watchlist: Boolean?
)
