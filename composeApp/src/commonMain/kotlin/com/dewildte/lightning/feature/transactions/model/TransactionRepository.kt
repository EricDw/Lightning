package com.dewildte.lightning.feature.transactions.model

interface TransactionRepository  {
    suspend fun retrieveAllTransactions() : List<Transaction>

    suspend fun insertTransaction(transaction: Transaction)

    suspend fun deleteTransaction(transactionId: TransactionId)

}