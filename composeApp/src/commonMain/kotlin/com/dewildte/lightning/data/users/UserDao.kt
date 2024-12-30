package com.dewildte.lightning.data.users

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: UserEntity)

    @Query("SELECT * FROM users_table")
    suspend fun retreiveAllUsers(): List<UserEntity>

    @Delete
    suspend fun deleteUser(user: UserEntity)
}