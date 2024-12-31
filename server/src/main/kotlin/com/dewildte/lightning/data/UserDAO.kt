package com.dewildte.lightning.data

import com.dewildte.lightning.models.users.User
import com.dewildte.lightning.models.users.UserId
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.util.*
import kotlin.uuid.toKotlinUuid

object UserTable : UUIDTable("users") {
    val email = varchar("email", 50)
    val password = varchar("password", 50)
}

class UserDAO(
    id: EntityID<UUID>
) : UUIDEntity(id) {
    companion object : UUIDEntityClass<UserDAO>(UserTable)

    var email by UserTable.email
    var password by UserTable.password
}

suspend fun <T> suspendTransaction(block: Transaction.() -> T): T =
    newSuspendedTransaction(Dispatchers.IO, statement = block)

fun daoToModel(dao: UserDAO) = User(
    id = UserId(dao.id.value.toKotlinUuid()),
)