package com.dewildte.lightning.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dewildte.lightning.data.LightningDatabase

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<LightningDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("lightning_room.db")
    return Room.databaseBuilder<LightningDatabase>(
      context = appContext,
      name = dbFile.absolutePath
    )
  }