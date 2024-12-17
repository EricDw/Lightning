package com.dewildte.lightning.dtos.transactions.data

import com.dewildte.lightning.dtos.tags.data.TagDTO
import kotlinx.serialization.Serializable

@Serializable
data class TransactionDTO(
    val id: TransactionIdDTO,
    val source: SourceDTO,
    val destination: DestinationDTO,
    val date: TransactionDateDTO,
    val note: NoteDTO,
    val money: MoneyDTO,
    val tags: List<TagDTO>,
)

