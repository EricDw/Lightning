package com.dewildte.lightning.application

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.filled.Cabin
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import lightning.composeapp.generated.resources.*
import org.jetbrains.compose.resources.StringResource

enum class AppDestination(
    val label: StringResource,
    val icon: ImageVector,
    val contentDescription: StringResource,
) {
    HOME(
        label = Res.string.label_home,
        icon = Icons.Default.Cabin,
        contentDescription = Res.string.description_home_icon,
    ),
    TRANSACTIONS(
        label = Res.string.label_transactions,
        icon = Icons.AutoMirrored.Default.ReceiptLong,
        contentDescription = Res.string.description_transactions_icon,
    ),
    SETTINGS(
        label = Res.string.label_settings,
        icon = Icons.Default.Settings,
        contentDescription = Res.string.label_settings,
    ),
}