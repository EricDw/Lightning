package com.dewildte.lightning.feature.onboarding

import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dewildte.lightning.application.model.LightningApplication
import com.dewildte.lightning.feature.onboarding.login.LoginScreenController
import com.dewildte.lightning.feature.onboarding.login.LoginScreenViewModel
import com.dewildte.lightning.feature.onboarding.splash.SplashScreenController
import kotlinx.serialization.Serializable

@Serializable
data object OnboardingRoute

@Serializable
data object SplashRoute

@Serializable
data object LoginRoute

fun NavGraphBuilder.onboardingNavigationGraph(
    model: LightningApplication,
    navController: NavController,
    navigateToHome: () -> Unit,
    onLaunched: () -> Unit,
) {

    navigation<OnboardingRoute>(
        startDestination = SplashRoute
    ) {

        composable<SplashRoute> {
            SplashScreenController(
                model = model,
                navigateToLogin = {
                    navController.navigate(route = LoginRoute)
                },
                navigateToHome = navigateToHome,
            )
        }

        composable<LoginRoute> { backStackEntry ->

            val viewModel: LoginScreenViewModel = viewModel {
                LoginScreenViewModel(
                    model = model
                )
            }

            LoginScreenController(
                viewModel = viewModel,
                navigateToHome = navigateToHome
            )

            LaunchedEffect(backStackEntry) {
                onLaunched()
            }
        }

    }

}