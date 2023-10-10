package com.example.moveeapp_compose_kmm.utils

import com.example.moveeapp_compose_kmm.BuildKonfig

object Constants {
    const val API_KEY = BuildKonfig.API_KEY_TMDB
    const val BASE_URL = BuildKonfig.BASE_URL
    const val IMAGE_BASE = BuildKonfig.IMAGE_BASE_URL
    const val REGISTER = BuildKonfig.REGISTER_URL
    const val FORGOT_PASSWORD = BuildKonfig.RESET_PASSWORD_URL
    const val MOVIE = "movie"
    const val TV = "tv"
    const val PERSON = "person"
}

object ShadredPrefConstants {
    const val KEY_SESSION_ID = "key_session_id"
    const val KEY_ACCOUNT_ID = "key_account_id"
}