package com.dewildte.lightning.design.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.dewildte.lightning.design.theme.LightningTheme
import com.dewildte.lightning.design.theme.icons
import lightning.composeapp.generated.resources.*
import lightning.composeapp.generated.resources.Res
import lightning.composeapp.generated.resources.description_clear_icon
import lightning.composeapp.generated.resources.label_password
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PasswordField(
    value: String,
    modifier: Modifier = Modifier,
    supportingText: String = "",
    revealed: Boolean = false,
    onValueChange: (newValue: String) -> Unit = { /* no-op */ },
    onRevealPasswordClick: () -> Unit = { /* no-op */ },
    onHidePasswordClick: () -> Unit = { /* no-op */ },
) {

    val visualTransformation = if (revealed) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    val trailingIcon: (@Composable () -> Unit)? = if (value.isNotEmpty()) {
        val icon: ImageVector
        val description: StringResource

        if (revealed) {
            icon = MaterialTheme.icons.revealPassword
            description = Res.string.description_reveal_password_icon
        } else {
            icon = MaterialTheme.icons.hidePassword
            description = Res.string.description_hide_password_icon
        }

        {
            IconButton(
                onClick = {
                    if (revealed) {
                        onHidePasswordClick()
                    } else {
                        onRevealPasswordClick()
                    }
                }
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = stringResource(description)
                )
            }
        }
    } else {
        null
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {
            val text = stringResource(Res.string.label_password)
            Text(text = text)
        },
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = visualTransformation,
        supportingText = {
            Text(supportingText)
        }
    )
}

@Preview
@Composable
private fun EmptyPasswordFieldPreview() {
    val value = remember {
        ""
    }
    LightningTheme(
        darkTheme = false
    ) {
        PasswordField(
            modifier = Modifier.padding(16.dp),
            value = value,
            onValueChange = { /* no-op */ }
        )
    }
}

@Preview
@Composable
private fun FilledPasswordFieldPreview() {
    val value = remember {
        "123456"
    }
    LightningTheme(
        darkTheme = false
    ) {
        PasswordField(
            modifier = Modifier.padding(16.dp),
            value = value,
            onValueChange = { /* no-op */ }
        )
    }
}