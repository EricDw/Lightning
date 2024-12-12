package com.dewildte.lightning

import com.dewildte.lightning.application.api.LightningApplication
import com.dewildte.lightning.feature.transactions.InMemoryTransactionRepository
import com.dewildte.lightning.feature.transactions.data.TransactionRepository
import kotlinx.coroutines.flow.StateFlow

class ServerLightningApplication(
    private val transactionRepository: TransactionRepository = InMemoryTransactionRepository()
) : LightningApplication {


    override suspend fun recieve(message: LightningApplication.Message) {
        when (message) {
            is LightningApplication.Message.GetPlatform -> TODO()
            is LightningApplication.Message.RetrieveTransactions -> {
                try {
                    val transactions = transactionRepository.retrieveAllTransactions()
                    message.response.complete(transactions)
                } catch (error: Throwable) {
                    message.response.completeExceptionally(error)
                }
            }
        }
    }
}