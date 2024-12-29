package com.dewildte.lightning

import androidx.compose.ui.window.ApplicationScope
import com.dewildte.lightning.application.RealLightningApplication
import com.dewildte.lightning.application.model.LightningApplication
import com.dewildte.lightning.models.users.User
import com.dewildte.lightning.models.users.UserId
import com.dewildte.lightning.network.FinanceApi
import com.dewildte.lightning.network.OnboardingApi
import com.dewildte.lightning.network.TransactionMapper
import com.dewildte.lightning.network.buildHttpClient

class DesktopLightningApplication(
    applicationScope: ApplicationScope
) : ApplicationScope by applicationScope, LightningApplication {

    private val realLightningApplication = RealLightningApplication()

    override suspend fun recieve(message: LightningApplication.Message) {
        realLightningApplication.recieve(message)
    }
}