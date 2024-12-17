package com.dewildte.lightning.feature.transactions.data

import com.dewildte.lightning.dtos.transactions.data.*
import com.dewildte.lightning.feature.tags.data.TagMapper
import com.dewildte.lightning.models.transactions.*
import kotlinx.datetime.LocalDate
import kotlin.uuid.Uuid

class TransactionMapper {
    val tagMapper = TagMapper()

    fun mapTransactionToTransactionDto(transaction: Transaction): TransactionDTO {
        return with(transaction) {
            TransactionDTO(
                id = TransactionIdDTO(value = id.value.toString()),
                source = SourceDTO(value = source.value),
                destination = DestinationDTO(destination.value),
                date = TransactionDateDTO(date.value.toString()),
                note = NoteDTO(note.value),
                money = MoneyDTO(money.value),
                tags = tags.map(tagMapper::mapTagToTagDto)
            )
        }
    }

    fun mapTransactionDtoToTransaction(dto: TransactionDTO): Transaction {
        return with(dto) {
            Transaction(
                id = TransactionId(value = Uuid.parse(id.value)),
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