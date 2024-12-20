package com.dewildte.lightning.application.model

import com.dewildte.lightning.models.transactions.Transaction
import com.dewildte.lightning.models.users.User
import kotlinx.coroutines.CompletableDeferred

interface LightningApplication {

    suspend fun recieve(message: Message)

    sealed class Message {

        data class RetrieveTransactions(
            val response: CompletableDeferred<List<Transaction>> = CompletableDeferred()
        ): Message()

        data class LoginWithEmailAndPassword(
            val email: String,
            val password: String,
            val response: CompletableDeferred<User> = CompletableDeferred()
        ): Message()
    }
}