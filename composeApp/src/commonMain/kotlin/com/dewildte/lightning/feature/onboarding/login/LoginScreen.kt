package com.dewildte.lightning.feature.onboarding.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dewildte.lightning.design.components.EmailField
import com.dewildte.lightning.design.components.PasswordField
import com.dewildte.lightning.design.theme.LightningTheme
import com.dewildte.lightning.design.theme.icons
import com.dewildte.lightning.models.users.User
import lightning.composeapp.generated.resources.Res
import lightning.composeapp.generated.resources.description_app_icon
import lightning.composeapp.generated.resources.label_login
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreenController(
    viewModel: LoginScreenViewModel,
    navigateToHome: () -> Unit = {}
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    var email by remember {
        mutableStateOf(state.email)
    }

    var password by remember {
        mutableStateOf(state.password)
    }

    var revealPassword by remember {
        mutableStateOf(false)
    }

    val enableSubmit = state.email.isNotBlank() &&
            state.password.isNotBlank()

    LoginScreen(
        email = email,
        password = password,
        revealPassword = revealPassword,
        enableSubmit = enableSubmit,
        onEmailChange = { newEmail ->
            email = newEmail
            viewModel.setEmail(newEmail = newEmail)
        },
        onPasswordChange = { newPassword ->
            password = newPassword
            viewModel.setPassword(
                newPassword = newPassword
            )
        },
        onRevealPasswordClick = {
            revealPassword = true
        },
        onHidePasswordClick = {
            revealPassword = false
        },
    )

    LaunchedEffect(state.user) {
        if (state.user != null) {
            navigateToHome()
        }
    }

}

@Composable
fun LoginScreen(
    email: String = "",
    password: String = "",
    revealPassword: Boolean = false,
    enableSubmit: Boolean = true,
    onEmailChange: (newEmail: String) -> Unit = { /* no-op */ },
    onPasswordChange: (newPassword: String) -> Unit = { /* no-op */ },
    onRevealPasswordClick: () -> Unit = { /* no-op */ },
    onHidePasswordClick: () -> Unit = { /* no-op */ },
    onSubmit: () -> Unit = {},
) {

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Box(
            modifier = Modifier.weight(.5F)
        ) {
            Icon(
                imageVector = MaterialTheme.icons.lightning,
                contentDescription = stringResource(Res.string.description_app_icon),
                modifier = Modifier.fillMaxSize()
            )
        }

        Column(
            modifier = Modifier.weight(.5F),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            EmailField(
                value = email,
                onValueChange = onEmailChange,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            PasswordField(
                value = password,
                revealed = revealPassword,
                onValueChange = onPasswordChange,
                modifier = Modifier.padding(horizontal = 16.dp),
                onRevealPasswordClick = onRevealPasswordClick,
                onHidePasswordClick = onHidePasswordClick,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onSubmit,
                enabled = enableSubmit,
            ) {
                Text(text = stringResource(Res.string.label_login))
            }

        }
    }

}

@Immutable
data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    val isLoggingIn: Boolean = false,
    val error: String? = null,
    val user: User? = null,
)

@Preview
@Composable
private fun EmptyLoginScreenPreview() {
    LightningTheme {
        LoginScreen(
            password = "12345"
        )
    }
}