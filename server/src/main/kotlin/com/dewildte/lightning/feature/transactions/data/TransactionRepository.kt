package com.dewildte.lightning.feature.transactions.data

import com.dewildte.lightning.feature.transactions.model.Transaction
import com.dewildte.lightning.feature.transactions.model.TransactionId

interface TransactionRepository  {
    suspend fun retrieveAllTransactions() : List<Transaction>

    suspend fun insertTransaction(transaction: Transaction)

    suspend fun deleteTransaction(transactionId: TransactionId)

}