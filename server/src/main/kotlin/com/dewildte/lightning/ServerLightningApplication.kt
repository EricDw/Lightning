package com.dewildte.lightning

import com.dewildte.lightning.LightningApplication.*
import com.dewildte.lightning.data.UserDAO
import com.dewildte.lightning.data.UserTable
import com.dewildte.lightning.data.daoToModel
import com.dewildte.lightning.data.suspendTransaction
import com.dewildte.lightning.dtos.transactions.data.InMemoryTransactionRepository
import com.dewildte.lightning.dtos.transactions.data.TransactionRepository
import com.dewildte.lightning.models.users.User
import com.dewildte.lightning.models.users.UserId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.uuid.Uuid

class ServerLightningApplication(
    private val transactionRepository: TransactionRepository = InMemoryTransactionRepository()
) : LightningApplication {

    private val users = mapOf(
        "dewildte@gmail.com" to "550e8400-e29b-41d4-a716-446655440000",
        "lauren.dewildt@gmail.com" to "550e8400-e29b-41d4-a716-446655440001",
    )

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

            is Message.LoginWithEmailAndPassword -> {

                val email = message.email

                try {

                    val cachedUser = suspendTransaction {
                        try {
                            UserDAO
                                .find {
                                    UserTable.email eq email.value
                                }
                                .limit(1)
                                .map(::daoToModel)
                                .firstOrNull()
                        } catch (e: Throwable) {
                            null
                        }
                    }

                    cachedUser?.let {
                        message.response.complete(it)
                        return
                    }

                    val id = users[email.value]
                    checkNotNull(id)

                    val userId = UserId(
                        value = Uuid.parse(id)
                    )
                    val user = User(
                        id = userId
                    )

                    if (message.email.value == "dewildte@gmail.com") {
                        suspendTransaction {
                            UserDAO.new(id = UUID.fromString(id)) {
                                this.email = "dewildte@gmail.com"
                                this.password = "Cat Couch Coffee$"
                            }
                        }
                    }

                    message.response.complete(user)
                } catch (error: Throwable) {
                    message.response.completeExceptionally(exception = error)
                }
            }
        }
    }
}