package com.dewildte.lightning.application

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.dewildte.lightning.design.theme.LightningTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LightningScaffold(
    selectedDestination: AppDestination? = null,
    onDestinationClick: (destination: AppDestination) -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    val darkTheme = isSystemInDarkTheme()

    val colorScheme = if (darkTheme) {
        darkColorScheme()
    } else {
        lightColorScheme()
    }

    val adaptiveInfo = currentWindowAdaptiveInfo()

    val windowWidthSizeClass = adaptiveInfo.windowSizeClass.windowWidthSizeClass
    val windowHeightSizeClass = adaptiveInfo.windowSizeClass.windowHeightSizeClass

    val layoutType = when {

        selectedDestination == null -> {
            NavigationSuiteType.None
        }

        windowHeightSizeClass == WindowHeightSizeClass.COMPACT -> {
            NavigationSuiteType.NavigationBar
        }

        windowWidthSizeClass == WindowWidthSizeClass.COMPACT -> {
            NavigationSuiteType.NavigationBar
        }

        windowWidthSizeClass == WindowWidthSizeClass.MEDIUM -> {
            NavigationSuiteType.NavigationRail

        }

        else -> {
            NavigationSuiteType.NavigationDrawer

        }
    }

    LightningTheme(
        colors = colorScheme
    ) {
        NavigationSuiteScaffold(
            layoutType = layoutType,
            navigationSuiteItems = {
                AppDestination.entries.forEach { destination ->
                    item(
                        alwaysShowLabel = false,
                        selected = destination == selectedDestination,
                        onClick = { onDestinationClick(destination) },
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = stringResource(destination.contentDescription)
                            )
                        },
                        label = {
                            Text(text = stringResource(destination.label))
                        },
                    )
                }
            },
        ) {
            content()
        }
    }

}

@Preview
@Composable
private fun LightningApplicationPreview() {
    LightningScaffold(
        selectedDestination = AppDestination.HOME,
    ) {

    }
}