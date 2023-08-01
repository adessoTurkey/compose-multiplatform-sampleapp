package com.example.moveeapp_compose_kmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform