package com.dewildte.lightning.finance

class InMemoryTransactionRepository : TransactionRepository {

    private val _transactions = mutableMapOf(
        TransactionId(value = "0") to ReadTransaction(
            id = TransactionId(value = "0"),
            source = "VISA",
            destination = "Big Box Store",
            note = "Chairs and things",
            amount = Money(value = -5)
        ),
        TransactionId(value = "1") to ReadTransaction(
            id = TransactionId(value = "1"),
            source = "VISA",
            note = "Soup",
            destination = "Resturant",
            amount = Money(value = 5)
        ),
    )

    override suspend fun retrieveAllTransactions(): List<ReadTransaction> {
        return _transactions.values.toList()
    }

    override suspend fun insertTransaction(transaction: WriteTransaction) {
        val readTransaction = with(transaction) {
            ReadTransaction(
                id = id,
                source = source,
                destination = destination,
                note = note,
                amount = amount
            )
        }

        _transactions[readTransaction.id] = readTransaction
    }

    override suspend fun deleteTransaction(transactionId: TransactionId) {
        _transactions.remove(transactionId)
    }

}