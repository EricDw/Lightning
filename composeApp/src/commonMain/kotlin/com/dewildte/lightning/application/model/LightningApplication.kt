package com.dewildte.lightning.application.model

import com.dewildte.lightning.models.transactions.Transaction
import kotlinx.coroutines.CompletableDeferred

interface LightningApplication {

    suspend fun recieve(message: Message)

    sealed class Message {

        data class RetrieveTransactions(
            val response: CompletableDeferred<List<Transaction>> = CompletableDeferred()
        ): Message()
    }
}