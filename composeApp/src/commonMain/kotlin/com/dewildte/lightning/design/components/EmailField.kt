package com.dewildte.lightning.design.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import lightning.composeapp.generated.resources.Res
import lightning.composeapp.generated.resources.label_email
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EmailField(
    value: String = "",
    supportingText: String = "",
    modifier: Modifier = Modifier,
    onValueChange: (newValue: String) -> Unit = { /* no-op */ },
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = {
            val text = stringResource(Res.string.label_email)
            Text(text = text)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        supportingText = {
            Text(supportingText)
        },
    )
}


@Preview
@Composable
private fun EmailFieldPreview() {
    val value = remember {
        ""
    }
    EmailField(
        modifier = Modifier.padding(16.dp),
        value = value,
        onValueChange = { /* no-op */ }
    )
}