package com.dewildte.lightning.feature.onboarding

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dewildte.lightning.application.model.LightningApplication
import com.dewildte.lightning.feature.onboarding.login.LoginScreenController
import com.dewildte.lightning.feature.onboarding.login.LoginScreenViewModel
import kotlinx.serialization.Serializable

@Serializable
data object OnboardingRoute

@Serializable
data object LoginRoute

fun NavGraphBuilder.onboardingNavigationGraph(
    model: LightningApplication,
    navigateToHome: () -> Unit,
) {

    navigation<OnboardingRoute>(
        startDestination = LoginRoute
    ) {

        composable<LoginRoute> {

            val viewModel: LoginScreenViewModel = viewModel {
                LoginScreenViewModel(
                    model = model
                )
            }

            LoginScreenController(
                viewModel = viewModel,
                navigateToHome = navigateToHome
            )
        }

    }

}