package com.dewildte.lightning.application

import com.dewildte.lightning.Platform
import com.dewildte.lightning.feature.transactions.data.TransactionDTO
import com.dewildte.lightning.feature.transactions.model.Transaction
import kotlinx.coroutines.CompletableDeferred

interface LightningApplication {

    suspend fun recieve(message: Message)

    sealed class Message {

        data class GetPlatform(
            val response: CompletableDeferred<Platform> = CompletableDeferred()
        ): Message()

        data class RetrieveTransactions(
            val response: CompletableDeferred<List<Transaction>> = CompletableDeferred()
        ): Message()
    }
}