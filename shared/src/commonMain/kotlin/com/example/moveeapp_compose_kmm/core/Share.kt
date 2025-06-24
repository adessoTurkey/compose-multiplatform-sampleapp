package com.example.moveeapp_compose_kmm.core

expect fun share(
    context: PlatformContext,
    title: String,
    description: String?,
    imageUrl: String? = null
)
