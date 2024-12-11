package com.dewildte.lightning.finance

interface TransactionRepository  {
    suspend fun retrieveAllTransactions() : List<TransactionDTO>

    suspend fun insertTransaction(transaction: TransactionModel)

    suspend fun deleteTransaction(identifierDTO: IdentifierDTO)

}