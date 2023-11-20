package com.example.moveeapp_compose_kmm.core

import dev.icerock.moko.resources.StringResource

actual val StringResource.key: String?
    get() {
        val context = AppContext.context ?: return null

        return context
            .resources
            .getResourceName(resourceId)
    }