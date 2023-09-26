package com.example.moveeapp_compose_kmm.core

import com.example.moveeapp_compose_kmm.utils.ShadredPrefConstants
import com.liftric.kvault.KVault


class SessionSettings(private val kvault: KVault) {

    fun setSessionId(newSessionId: String) {
        kvault.set(ShadredPrefConstants.KEY_SESSION_ID, newSessionId)
    }

    fun getSessionId(): String? {
        return kvault.string(ShadredPrefConstants.KEY_SESSION_ID)
    }

    fun setInt(value: Int, key: String) {
        kvault.set(key, value)
    }

    fun getInt(key: String): Int? {
        return kvault.int(key)
    }

}
