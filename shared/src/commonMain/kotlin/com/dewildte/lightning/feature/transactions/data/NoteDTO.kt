package com.dewildte.lightning.feature.transactions.data

import kotlinx.serialization.Serializable

@Serializable
data class NoteDTO(
    val value: String
)