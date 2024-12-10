package com.dewildte.lightning.design.components

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import com.dewildte.lightning.design.theme.LightningTheme
import com.dewildte.lightning.navigation.AppDestination
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LightningApplicationScaffold(
    state: LightningApplicationScaffoldState,
    selectedDestination: AppDestination? = null,
    content: @Composable () -> Unit = {}
) {

    LightningTheme {
        NavigationSuiteScaffold(
            navigationSuiteItems = {
                AppDestination.entries.forEach { destination ->
                    item(
                        selected = destination == selectedDestination,
                        onClick = {},
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = stringResource(destination.contentDescription)
                            )
                        },
                        label = {
                            Text(text = stringResource(destination.label))
                        }
                    )
                }
            },
        ) {
            content()
        }
    }
}


@Immutable
data class LightningApplicationScaffoldState(
    val isLoading: Boolean = true,
)

@Preview
@Composable
private fun LightningApplicationPreview() {
    val state = remember { LightningApplicationScaffoldState() }
    LightningApplicationScaffold(
        state = state,
        selectedDestination = AppDestination.HOME,
    ) {

    }
}