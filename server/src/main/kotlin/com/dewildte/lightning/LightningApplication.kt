package com.dewildte.lightning.application.api

import com.dewildte.lightning.Platform
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