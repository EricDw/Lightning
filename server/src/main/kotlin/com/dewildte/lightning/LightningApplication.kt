package com.dewildte.lightning

import com.dewildte.lightning.models.email.EmailAddress
import com.dewildte.lightning.models.password.Password
import com.dewildte.lightning.models.transactions.Transaction
import com.dewildte.lightning.models.users.User
import kotlinx.coroutines.CompletableDeferred

interface LightningApplication {

    suspend fun recieve(message: Message)
    
    sealed class Message {
        data class RetrieveTransactions(
            val response: CompletableDeferred<List<Transaction>> = CompletableDeferred()
        ): Message()

        data class LoginUserWithEmailAndPassword(
            val email: EmailAddress,
            val password: Password,
            val response: CompletableDeferred<User> = CompletableDeferred()
        ): Message()
    }
}