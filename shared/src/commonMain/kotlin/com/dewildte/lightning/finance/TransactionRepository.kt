package com.dewildte.lightning.finance

interface TransactionRepository  {
    suspend fun retrieveAllTransactions() : List<ReadTransaction>

    suspend fun insertTransaction(transaction: WriteTransaction)

    suspend fun deleteTransaction(transactionId: TransactionId)

}