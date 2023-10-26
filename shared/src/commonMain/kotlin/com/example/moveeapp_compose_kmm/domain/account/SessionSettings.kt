package com.example.moveeapp_compose_kmm.domain.account

import com.example.moveeapp_compose_kmm.domain.SettingsProvider

private const val KEY_SESSION_ID = "key_session_id"
private const val KEY_ACCOUNT_ID = "key_account_id"

class SessionSettings(private val settingsProvider: SettingsProvider) {

    fun setSessionId(newSessionId: String) = settingsProvider.set(KEY_SESSION_ID, newSessionId)

    fun getSessionId(): String? = settingsProvider.string(KEY_SESSION_ID)

    fun setAccountId(id: Int) = settingsProvider.set(KEY_ACCOUNT_ID, id)

    fun getAccountId(): Int? = settingsProvider.int(KEY_ACCOUNT_ID)
}
