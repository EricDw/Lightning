package com.dewildte.lightning.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import lightning.composeapp.generated.resources.Res
import lightning.composeapp.generated.resources.label_home
import lightning.composeapp.generated.resources.label_settings
import org.jetbrains.compose.resources.StringResource

enum class AppDestination(
    val label: StringResource,
    val icon: ImageVector,
    val contentDescription: StringResource
) {
    HOME(
        Res.string.label_home,
        Icons.Default.Home,
        Res.string.label_home,
    ),
    SETTINGS(
        Res.string.label_settings,
        Icons.Default.Settings,
        Res.string.label_settings,
    ),
}