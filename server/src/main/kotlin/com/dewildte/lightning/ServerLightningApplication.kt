package com.dewildte.lightning

import com.dewildte.lightning.LightningApplication.*
import com.dewildte.lightning.data.UserDAO
import com.dewildte.lightning.data.UserTable
import com.dewildte.lightning.data.daoToModel
import com.dewildte.lightning.data.suspendTransaction
import com.dewildte.lightning.dtos.transactions.data.InMemoryTransactionRepository
import com.dewildte.lightning.dtos.transactions.data.TransactionRepository
import com.dewildte.lightning.feature.users.UserRepository
import com.dewildte.lightning.models.users.User
import com.dewildte.lightning.models.users.Username
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.uuid.Uuid

class ServerLightningApplication(
    private val transactionRepository: TransactionRepository,
    private val userRepository: UserRepository,
) : LightningApplication {
//
//    private val users = mapOf(
//        "dewildte@gmail.com" to "b11ca74b-98ae-4c24-91fd-9c98500f86aa",
//        "lauren.dewildt@gmail.com" to "a72d33da-32da-417a-acc5-236cad056cca",
//    )

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

                val username = message.email

                try {

                    val cachedUser = suspendTransaction {
                        try {
                            UserDAO
                                .find {
                                    UserTable.username eq username.value
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

                    throw IllegalStateException("User: $username not found.")

                } catch (error: Throwable) {
                    message.response.completeExceptionally(exception = error)
                }
            }
        }
    }
}