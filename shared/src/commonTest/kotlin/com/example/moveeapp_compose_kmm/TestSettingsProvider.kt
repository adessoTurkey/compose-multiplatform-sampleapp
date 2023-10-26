package com.example.moveeapp_compose_kmm

import com.example.moveeapp_compose_kmm.domain.SettingsProvider

class TestSettingsProvider : SettingsProvider {
    private val data = mutableMapOf<String, Any>()

    override fun set(key: String, value: Boolean): Boolean {
        data[key] = value
        return true
    }

    override fun set(key: String, value: Double): Boolean {
        data[key] = value
        return true
    }

    override fun set(key: String, value: Float): Boolean {
        data[key] = value
        return true
    }

    override fun set(key: String, value: Int): Boolean {
        data[key] = value
        return true
    }

    override fun set(key: String, value: Long): Boolean {
        data[key] = value
        return true
    }

    override fun set(key: String, value: String): Boolean {
        data[key] = value
        return true
    }

    override fun bool(forKey: String): Boolean? = data[forKey] as? Boolean
    override fun int(forKey: String): Int? = data[forKey] as? Int
    override fun float(forKey: String): Float? = data[forKey] as? Float
    override fun double(forKey: String): Double? = data[forKey] as? Double
    override fun long(forKey: String): Long? = data[forKey] as? Long
    override fun string(forKey: String): String? = data[forKey] as? String

    override fun keys(): List<String> = data.keys.toList()
    override fun exists(forKey: String): Boolean = data.containsKey(forKey)

    override fun delete(forKey: String): Boolean {
        data.remove(forKey)
        return true
    }

    override fun clear(): Boolean {
        data.clear()
        return true
    }
}
