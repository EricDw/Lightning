package com.dewildte.lightning.application

import com.dewildte.lightning.application.model.LightningApplication
import com.dewildte.lightning.data.users.UserDao
import com.dewildte.lightning.data.users.UserEntity
import com.dewildte.lightning.models.users.User
import com.dewildte.lightning.models.users.UserId
import com.dewildte.lightning.network.FinanceApi
import com.dewildte.lightning.network.OnboardingApi
import com.dewildte.lightning.network.TransactionMapper
import com.dewildte.lightning.network.buildHttpClient
import java.lang.IllegalStateException

class RealLightningApplication(
    private val userDao: UserDao,
) : LightningApplication {
    private var currentUsername: String = ""
    private var currentPassword = ""
    private var currentUser: User? = null

    private val httpClient by lazy {
        buildHttpClient()
    }

    private val financeApi by lazy {
        FinanceApi(
            httpClient = httpClient
        )
    }

    private val onboardingApi by lazy {
        OnboardingApi(
            httpClient = httpClient
        )
    }

    override suspend fun recieve(message: LightningApplication.Message) {
        when (message) {
            is LightningApplication.Message.RetrieveTransactions -> {
                try {
                    val mapper = TransactionMapper()
                    val transactions = financeApi.getTransactions(
                        username = currentUsername,
                        password = currentPassword,
                    )
                        .map(mapper::mapTransactionDtoToTransaction)
                    message.response.complete(transactions)
                } catch (error: Throwable) {
                    message.response.completeExceptionally(error)
                }
            }

            is LightningApplication.Message.TryLogin -> {

                val cachedUser = userDao
                    .retreiveAllUsers()
                    .firstOrNull()

                cachedUser?.let { (id, email, password) ->
                    currentUsername = email
                    currentPassword = password
                    val user = User(
                        id = UserId(value = id)
                    )
                    currentUser = user
                    message.response.complete(user)
                    return
                }

                message.response.completeExceptionally(
                    exception = IllegalStateException()
                )


            }

            is LightningApplication.Message.LoginWithEmailAndPassword -> {
                currentUser?.let { user ->
                    message.response.complete(user)
                    return
                }

                try {

                    val user = onboardingApi.login(
                        username = message.email,
                        password = message.password,
                    )

                    currentUser = user
                    currentUsername = message.email
                    currentPassword = message.password

                    val userEntity = UserEntity(
                        id = user.id.value,
                        email = message.email,
                        password = message.password,
                    )

                    userDao.insert(userEntity)

                    message.response.complete(user)
                } catch (e: Throwable) {
                    message.response.completeExceptionally(e)
                }
            }
        }
    }

}