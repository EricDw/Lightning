package com.dewildte.lightning.feature.transactions.data

import com.dewildte.lightning.feature.tags.data.TagMapper
import com.dewildte.lightning.feature.transactions.model.*

class TransactionMapper {
    fun mapTransactionToTransactionDto(transaction: Transaction): TransactionDTO {
        val tagMapper = TagMapper()
        return with(transaction) {
            TransactionDTO(
                id = TransactionIdDTO(value = id.value),
                source = SourceDTO(value = source.value),
                destination = DestinationDTO(destination.value),
                note = NoteDTO(note.value),
                money = MoneyDTO(money.value),
                tags = tags.map(tagMapper::mapTagToTagDto)
            )
        }
    }

    fun mapTransactionDtoToTransaction(dto: TransactionDTO): Transaction {
        val tagMapper = TagMapper()
        return with(dto) {
            Transaction(
                id = TransactionId(value = id.value),
                source = Source(value = source.value),
                destination = Destination(destination.value),
                note = Note(note.value),
                money = Money(money.value),
                tags = tags.map(tagMapper::mapTagDtoToTag)
            )
        }
    }
}