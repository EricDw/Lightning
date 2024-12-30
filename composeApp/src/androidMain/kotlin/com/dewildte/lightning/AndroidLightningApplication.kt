package com.dewildte.lightning

import android.app.Application
import com.dewildte.lightning.application.RealLightningApplication
import com.dewildte.lightning.application.model.LightningApplication
import com.dewildte.lightning.data.LightningDatabase
import com.dewildte.lightning.data.getRoomDatabase
import com.dewildte.lightning.database.getDatabaseBuilder
import com.dewildte.lightning.models.users.User
import com.dewildte.lightning.models.users.UserId
import com.dewildte.lightning.network.TransactionMapper
import com.dewildte.lightning.network.FinanceApi
import com.dewildte.lightning.network.OnboardingApi
import com.dewildte.lightning.network.buildHttpClient

class AndroidLightningApplication : Application(), LightningApplication {

    private lateinit var realLightningApplication: RealLightningApplication

    private lateinit var database: LightningDatabase

    override fun onCreate() {
        super.onCreate()

        database = getRoomDatabase(getDatabaseBuilder(this))

        realLightningApplication = RealLightningApplication(
            userDao = database.getUserDao()
        )
    }

    override suspend fun recieve(message: LightningApplication.Message) {
        realLightningApplication.recieve(message)
    }
}