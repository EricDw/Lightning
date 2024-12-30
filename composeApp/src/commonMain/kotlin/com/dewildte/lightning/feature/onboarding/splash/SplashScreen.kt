package com.dewildte.lightning.feature.onboarding.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dewildte.lightning.application.model.LightningApplication
import com.dewildte.lightning.design.theme.LightningTheme
import com.dewildte.lightning.design.theme.icons
import com.dewildte.lightning.models.users.User
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SplashScreenController(
    model: LightningApplication,
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit,
) {

    val viewModel = viewModel {
        SplashScreenViewModel(
            model = model,
        )
    }

    val state: SplashScreenState by viewModel.state.collectAsStateWithLifecycle()

    SplashScreen()

    LaunchedEffect(state) {
        when (state) {
            is SplashScreenState.Intialized -> {
                viewModel.tryLogin()
            }
            is SplashScreenState.Loading -> {
                /* no-op */
            }
            is SplashScreenState.UserFound -> {
                navigateToHome()
            }
            is SplashScreenState.UserNotFound -> {
                navigateToLogin()
            }
        }
    }

}

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {

        Icon(
            modifier = Modifier.padding(16.dp).fillMaxSize(),
            imageVector = MaterialTheme.icons.lightning,
            contentDescription = null
        )

    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    LightningTheme {
        SplashScreen()
    }
}

sealed class SplashScreenState {
    @Immutable
    data object Intialized : SplashScreenState()

    @Immutable
    data object Loading : SplashScreenState()

    @Immutable
    data object UserNotFound : SplashScreenState()

    @Immutable
    data class UserFound(
        val user: User
    ) : SplashScreenState()
}