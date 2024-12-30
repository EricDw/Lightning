package com.dewildte.lightning

import androidx.compose.ui.window.ApplicationScope
import com.dewildte.lightning.application.RealLightningApplication
import com.dewildte.lightning.application.model.LightningApplication
import com.dewildte.lightning.data.LightningDatabase
import com.dewildte.lightning.data.getRoomDatabase
import com.dewildte.lightning.database.getDatabaseBuilder
import com.dewildte.lightning.models.users.User
import com.dewildte.lightning.models.users.UserId
import com.dewildte.lightning.network.FinanceApi
import com.dewildte.lightning.network.OnboardingApi
import com.dewildte.lightning.network.TransactionMapper
import com.dewildte.lightning.network.buildHttpClient

class DesktopLightningApplication(
    applicationScope: ApplicationScope
) : ApplicationScope by applicationScope, LightningApplication {

    private val database: LightningDatabase =
        getRoomDatabase(getDatabaseBuilder())

    private val realLightningApplication = RealLightningApplication(
        userDao = database.getUserDao(),
    )

    override suspend fun recieve(message: LightningApplication.Message) {
        realLightningApplication.recieve(message)
    }
}