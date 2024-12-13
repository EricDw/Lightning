package com.dewildte.lightning.feature.transactions.data

import com.dewildte.lightning.feature.tags.data.TagMapper
import com.dewildte.lightning.feature.transactions.model.*
import kotlinx.datetime.LocalDate
import kotlin.uuid.Uuid

class TransactionMapper {
    val tagMapper = TagMapper()

    fun mapTransactionToTransactionDto(transaction: Transaction): TransactionDTO {
        return with(transaction) {
            TransactionDTO(
                id = TransactionIdDTO(value = id.value),
                source = SourceDTO(value = source.value),
                destination = DestinationDTO(destination.value),
                date = TransactionDateDTO(value = date.value.toString()),
                note = NoteDTO(note.value),
                money = MoneyDTO(money.value),
                tags = tags.map(tagMapper::mapTagToTagDto)
            )
        }
    }

    fun mapTransactionDtoToTransaction(dto: TransactionDTO): Transaction {
        return with(dto) {
            Transaction(
                id = TransactionId(value = id.value),
                source = Source(value = source.value),
                destination = Destination(destination.value),
                date = TransactionDate(value = LocalDate.parse(date.value)),
                note = Note(note.value),
                money = Money(money.value),
                tags = tags.map(tagMapper::mapTagDtoToTag)
            )
        }
    }
}