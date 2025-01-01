package com.dewildte.lightning.data

import com.dewildte.lightning.feature.users.UserRepository
import com.dewildte.lightning.models.email.EmailAddress
import com.dewildte.lightning.models.users.User

class PostgresUserRepository : UserRepository {
    override suspend fun retreiveAllUsers(): List<User> {
        return suspendTransaction { UserDAO.all().map(::daoToModel) }
    }

    override suspend fun retrieveUserByEmail(email: EmailAddress): User? {
        return suspendTransaction {
            UserDAO
                .find {
                    UserTable.username eq email.value
                }
                .limit(1)
                .map(::daoToModel)
                .firstOrNull()
        }
    }

}