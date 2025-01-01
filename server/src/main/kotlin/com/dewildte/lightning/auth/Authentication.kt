package com.dewildte.lightning.auth

import com.dewildte.lightning.data.UserDAO
import com.dewildte.lightning.data.UserTable
import com.dewildte.lightning.feature.users.UserRepository
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.util.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureAuthentication(
    userRepository: UserRepository
) {

    val digestFunction = getDigestFunction(
        algorithm = "SHA-256"
    ) { password ->
        "ltng${password.length}"
    }

    val hashedUserTable = runBlocking {
        val cachedUsers = userRepository.retreiveAllUsers()
            .associate {
                it.username.value to digestFunction(it.password.value)
            }

        UserHashedTableAuth(
            table = cachedUsers,
            digester = digestFunction,
        )
    }
    
    install(Authentication) {
        basic("auth-basic-hashed") {
            realm = "Access to the '/' path"
            validate { credentials ->
                hashedUserTable.authenticate(credentials)
            }
        }

        basic("auth-basic") {
            realm = "Access to the '/' path"
            validate { credentials ->
                val user = UserDAO
                    .find {
                        (UserTable.username eq credentials.name) and
                                (UserTable.password eq credentials.password)
                    }
                    .limit(1)
                    .firstOrNull()

                user?.let { UserIdPrincipal(it.username) }
            }
        }
    }

}