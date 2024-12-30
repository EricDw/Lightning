package com.dewildte.lightning.data.users

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "users_table"
)
data class UserEntity(
    @PrimaryKey
    val id: String,
    val email: String,
    val password: String,
)