package com.dewildte.lightning

import com.dewildte.lightning.LightningApplication.*
import com.dewildte.lightning.dtos.transactions.data.InMemoryTransactionRepository
import com.dewildte.lightning.dtos.transactions.data.TransactionRepository
import com.dewildte.lightning.models.users.User
import com.dewildte.lightning.models.users.UserId
import kotlin.uuid.Uuid

class ServerLightningApplication(
    private val transactionRepository: TransactionRepository = InMemoryTransactionRepository()
) : LightningApplication {


    override suspend fun recieve(message: Message) {
        when (message) {
            is Message.RetrieveTransactions -> {
                try {
                    val transactions = transactionRepository.retrieveAllTransactions()
                    message.response.complete(transactions)
                } catch (error: Throwable) {
                    message.response.completeExceptionally(error)
                }
            }

            is Message.LoginUserWithEmailAndPassword -> {
                // TODO: Load from database
                val email = message.email

                try {

                    check(email.value == "dewildte@gmail.com")

                    val id = "550e8400-e29b-41d4-a716-446655440000"
                    val userId = UserId(
                        value = Uuid.parse(id)
                    )
                    val user = User(
                        id = userId
                    )

                    message.response.complete(user)
                } catch (error: Throwable) {
                    message.response.completeExceptionally(exception = error)
                }
            }
        }
    }
}