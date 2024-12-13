package com.dewildte.lightning.feature.tags.data

import kotlinx.serialization.Serializable

@Serializable
data class TagDTO(
    val id: TagIdDTO,
    val label: TagLabelDTO,
)