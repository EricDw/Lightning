package com.dewildte.lightning.feature.transactions.data

import com.dewildte.lightning.feature.tags.data.TagDTO
import kotlinx.serialization.Serializable

@Serializable
data class TransactionDTO(
    val id: TransactionIdDTO,
    val source: SourceDTO,
    val destination: DestinationDTO,
    val note: NoteDTO,
    val money: MoneyDTO,
    val tags: List<TagDTO>,
)

