package com.example.moveeapp_compose_kmm.data

import com.example.moveeapp_compose_kmm.domain.SettingsProvider
import com.liftric.kvault.KVault

class KVaultSettingsProvider(private val kVault: KVault) : SettingsProvider {
    override fun set(key: String, value: Boolean): Boolean = kVault.set(key, value)
    override fun set(key: String, value: Double): Boolean = kVault.set(key, value)
    override fun set(key: String, value: Float): Boolean = kVault.set(key, value)
    override fun set(key: String, value: Int): Boolean = kVault.set(key, value)
    override fun set(key: String, value: Long): Boolean = kVault.set(key, value)
    override fun set(key: String, value: String): Boolean = kVault.set(key, value)

    override fun bool(forKey: String): Boolean? = kVault.bool(forKey)
    override fun int(forKey: String): Int? = kVault.int(forKey)
    override fun float(forKey: String): Float? = kVault.float(forKey)
    override fun double(forKey: String): Double? = kVault.double(forKey)
    override fun long(forKey: String): Long? = kVault.long(forKey)
    override fun string(forKey: String): String? = kVault.string(forKey)

    override fun keys(): List<String> = kVault.allKeys()
    override fun exists(forKey: String): Boolean = kVault.existsObject(forKey)
    override fun delete(forKey: String): Boolean = kVault.deleteObject(forKey)
    override fun clear(): Boolean = kVault.clear()
}
