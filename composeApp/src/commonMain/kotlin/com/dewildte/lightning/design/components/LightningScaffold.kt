package com.dewildte.lightning.design.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.dewildte.lightning.application.AppDestination
import com.dewildte.lightning.design.theme.LightningTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LightningScaffold(
    modifier: Modifier = Modifier,
    selectedDestination: AppDestination? = null,
    onDestinationClick: (destination: AppDestination) -> Unit = {},
    content: @Composable () -> Unit = {}
) {

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
            NavigationSuiteType.NavigationRail
        }
    }

    NavigationSuiteScaffold(
        modifier = modifier,
        layoutType = layoutType,
        navigationSuiteItems = {
            AppDestination.entries.forEachIndexed { index, destination ->
                val itemModifier = when (layoutType) {

                    NavigationSuiteType.NavigationRail -> {
                        val topPadding = if (index == 0) {
                            16.dp
                        } else {
                            0.dp
                        }
                        Modifier.padding(
                            start = 12.dp,
                            top = topPadding
                        )
                    }

                    else -> {
                        Modifier.Companion
                    }
                }
                item(
                    modifier = itemModifier,
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

@Preview
@Composable
private fun LightningApplicationPreview() {
    LightningTheme {
        LightningScaffold(
            selectedDestination = AppDestination.HOME,
        ) {
            ContentIsland()
        }
    }
}

@Preview
@Composable
private fun EmptyLightningApplicationPreview() {
    LightningTheme {
        LightningScaffold {
            ContentIsland()
        }
    }
}

@Composable
private fun ContentIsland() {
    LargeIsland(
        modifier = Modifier.padding(16.dp).fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text("Content Island")
        }
    }
}