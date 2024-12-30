package com.dewildte.lightning.data

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.dewildte.lightning.data.users.UserDao
import com.dewildte.lightning.data.users.UserEntity
import kotlinx.coroutines.Dispatchers

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = true,
)
@ConstructedBy(LightningDatabaseConstructor::class)
abstract class LightningDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object LightningDatabaseConstructor : RoomDatabaseConstructor<LightningDatabase> {
    override fun initialize(): LightningDatabase
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<LightningDatabase>
): LightningDatabase {
  return builder
      .setDriver(BundledSQLiteDriver())
      .setQueryCoroutineContext(Dispatchers.IO)
      .fallbackToDestructiveMigration(true)
      .build()
}