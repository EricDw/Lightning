package com.dewildte.lightning.feature.tags.data

import com.dewildte.lightning.feature.tags.data.TagIdDTO
import com.dewildte.lightning.feature.tags.data.TagLabelDTO
import kotlinx.serialization.Serializable

@Serializable
data class TagDTO(
    val id: TagIdDTO,
    val label: TagLabelDTO,
)