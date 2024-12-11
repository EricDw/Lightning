package com.dewildte.lightning.finance

class InMemoryTransactionRepository : TransactionRepository {

    private val _transactions = mutableMapOf(
        IdentifierDTO(value = "0") to TransactionDTO(
            id = IdentifierDTO(value = "0"),
            source = "VISA",
            destination = "Big Box Store",
            note = "Bought chairs",
            moneyDTO = MoneyDTO(value = -5),
            tags = listOf(
                TagDTO(
                    identifierDTO = IdentifierDTO("0"),
                    label = "Household"
                )
            )
        ),
        IdentifierDTO(value = "1") to TransactionDTO(
            id = IdentifierDTO(value = "1"),
            source = "Big Box Store",
            destination = "VISA",
            note = "Returned chairs",
            moneyDTO = MoneyDTO(value = 5),
            tags = listOf(
                TagDTO(
                    identifierDTO = IdentifierDTO("0"),
                    label = "Household"
                )
            ),
        ),
    )

    override suspend fun retrieveAllTransactions(): List<TransactionDTO> {
        return _transactions.values.toList()
    }

    override suspend fun insertTransaction(transaction: TransactionModel) {
        val transactionDTO = with(transaction) {
            TransactionDTO(
                id = id,
                source = source,
                destination = destination,
                note = note,
                moneyDTO = moneyDTO,
                tags = emptyList(),
            )
        }

        _transactions[transactionDTO.id] = transactionDTO
    }

    override suspend fun deleteTransaction(identifierDTO: IdentifierDTO) {
        _transactions.remove(identifierDTO)
    }

}