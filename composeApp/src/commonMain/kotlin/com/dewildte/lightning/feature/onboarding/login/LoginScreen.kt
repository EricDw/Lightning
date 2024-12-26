package com.dewildte.lightning.feature.onboarding.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import com.dewildte.lightning.models.users.User
import lightning.composeapp.generated.resources.Res
import lightning.composeapp.generated.resources.label_login
import lightning.composeapp.generated.resources.message_invalid_credentials
import lightning.composeapp.generated.resources.message_unknown_error
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
        error = state.error,
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
        onSubmit = viewModel::loginWithEmailAndPassword
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
    error: LoginError? = null,
    revealPassword: Boolean = false,
    enableSubmit: Boolean = true,
    onEmailChange: (newEmail: String) -> Unit = { /* no-op */ },
    onPasswordChange: (newPassword: String) -> Unit = { /* no-op */ },
    onRevealPasswordClick: () -> Unit = { /* no-op */ },
    onHidePasswordClick: () -> Unit = { /* no-op */ },
    onSubmit: () -> Unit = {},
) {

    Column(
        Modifier.verticalScroll(state = rememberScrollState()).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        EmailField(
            value = email,
            isError = error != null,
            onValueChange = onEmailChange,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        PasswordField(
            value = password,
            isError = error != null,
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

        if (error != null) {
            Spacer(modifier = Modifier.height(16.dp))
            val message = when (error) {
                is LoginError.InvalidCredentials -> {
                    stringResource(Res.string.message_invalid_credentials)
                }

                is LoginError.Unknown -> {
                    stringResource(Res.string.message_unknown_error, error.cause)
                }
            }
            Text(text = message, style = MaterialTheme.typography.bodyLarge)
        }

    }

}

@Immutable
data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    val isLoggingIn: Boolean = false,
    val error: LoginError? = null,
    val user: User? = null,
)

sealed class LoginError {
    @Immutable
    data object InvalidCredentials : LoginError()

    @Immutable
    data class Unknown(
        val cause: String
    ) : LoginError()
}

@Preview
@Composable
private fun EmptyLoginScreenPreview() {
    LightningTheme {
        LoginScreen()
    }
}

@Preview
@Composable
private fun FilledLoginScreenPreview() {
    LightningTheme {
        LoginScreen(
            email = "preview@gmail.com",
            password = "12345",
        )
    }
}

@Preview
@Composable
private fun InvalidCredentialsLoginScreenPreview() {
    LightningTheme {
        LoginScreen(
            email = "preview@gmail.com",
            password = "12345",
            error = LoginError.InvalidCredentials,
        )
    }
}

@Preview
@Composable
private fun UnknownErrorLoginScreenPreview() {
    LightningTheme {
        LoginScreen(
            email = "preview@gmail.com",
            password = "12345",
            error = LoginError.Unknown("Bad Network Connection"),
        )
    }
}