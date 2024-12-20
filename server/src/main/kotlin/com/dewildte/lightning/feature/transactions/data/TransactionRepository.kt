package com.dewildte.lightning.dtos.transactions.data

import com.dewildte.lightning.models.transactions.Transaction
import com.dewildte.lightning.models.transactions.TransactionId

interface TransactionRepository  {
    suspend fun retrieveAllTransactions() : List<Transaction>

    suspend fun insertTransaction(transaction: Transaction)

    suspend fun deleteTransaction(transactionId: TransactionId)

}