package com.example.moveeapp_compose_kmm.domain

interface SettingsProvider {
    fun set(key: String, value: Boolean): Boolean
    fun set(key: String, value: Double): Boolean
    fun set(key: String, value: Float): Boolean
    fun set(key: String, value: Int): Boolean
    fun set(key: String, value: Long): Boolean
    fun set(key: String, value: String): Boolean

    fun bool(forKey: String): Boolean?
    fun int(forKey: String): Int?
    fun float(forKey: String): Float?
    fun double(forKey: String): Double?
    fun long(forKey: String): Long?
    fun string(forKey: String): String?

    fun keys(): List<String>
    fun exists(forKey: String): Boolean
    fun delete(forKey: String): Boolean
    fun clear(): Boolean
}
