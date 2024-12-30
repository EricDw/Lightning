package com.dewildte.lightning.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.dewildte.lightning.data.LightningDatabase
import java.io.File

fun getDatabaseBuilder(): RoomDatabase.Builder<LightningDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "lightning_room.db")
    return Room.databaseBuilder<LightningDatabase>(
        name = dbFile.absolutePath,
    )
}