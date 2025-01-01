package com.dewildte.lightning.feature.users

import com.dewildte.lightning.models.email.EmailAddress
import com.dewildte.lightning.models.users.User

interface UserRepository {

    suspend fun retreiveAllUsers(): List<User>

    suspend fun retrieveUserByEmail(
        email: EmailAddress
    ): User?

}