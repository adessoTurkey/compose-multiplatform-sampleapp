package com.example.moveeapp_compose_kmm.ui.scene.actordetail

import com.example.moveeapp_compose_kmm.data.uimodel.ActorDetailUiModel

data class ActorDetailUiState (
    val isLoading: Boolean = true,
    val actorDetailData: ActorDetailUiModel = ActorDetailUiModel(),
    val error: String? = null
)