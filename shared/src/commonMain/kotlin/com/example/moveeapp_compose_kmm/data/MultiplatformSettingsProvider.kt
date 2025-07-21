package com.example.moveeapp_compose_kmm.data

import com.example.moveeapp_compose_kmm.domain.SettingsProvider
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

class MultiplatformSettingsProvider(private val settings: Settings) : SettingsProvider {
    override fun set(key: String, value: Boolean): Boolean {
        settings.putBoolean(key, value)
        return true
    }

    override fun set(key: String, value: Double): Boolean {
        settings[key] = value
        return true
    }

    override fun set(key: String, value: Float): Boolean {
        settings[key] = value
        return true
    }

    override fun set(key: String, value: Int): Boolean {
        settings[key] = value
        return true
    }

    override fun set(key: String, value: Long): Boolean {
        settings[key] = value
        return true
    }

    override fun set(key: String, value: String): Boolean {
        settings[key] = value
        return true
    }

    override fun bool(forKey: String): Boolean? = settings[forKey]

    override fun int(forKey: String): Int? = settings[forKey]

    override fun float(forKey: String): Float? = settings[forKey]

    override fun double(forKey: String): Double? = settings[forKey]

    override fun long(forKey: String): Long? = settings[forKey]

    override fun string(forKey: String): String? = settings[forKey]

    override fun keys(): List<String> = settings.keys.toList()

    override fun exists(forKey: String): Boolean = settings.hasKey(forKey)

    override fun delete(forKey: String): Boolean {
        settings.remove(forKey)
        return true
    }

    override fun clear(): Boolean {
        settings.clear()
        return true
    }
}
