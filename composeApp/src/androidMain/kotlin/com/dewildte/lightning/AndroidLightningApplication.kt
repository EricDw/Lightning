package com.dewildte.lightning

import android.app.Application
import com.dewildte.lightning.application.RealLightningApplication
import com.dewildte.lightning.application.model.LightningApplication
import com.dewildte.lightning.models.users.User
import com.dewildte.lightning.models.users.UserId
import com.dewildte.lightning.network.TransactionMapper
import com.dewildte.lightning.network.FinanceApi
import com.dewildte.lightning.network.OnboardingApi
import com.dewildte.lightning.network.buildHttpClient

class AndroidLightningApplication : Application(), LightningApplication {

    private lateinit var realLightningApplication: RealLightningApplication

    override fun onCreate() {
        super.onCreate()

        realLightningApplication = RealLightningApplication()
    }

    override suspend fun recieve(message: LightningApplication.Message) {
        realLightningApplication.recieve(message)
    }
}