package utils

import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher

/**
 * Matches a nodes with the given [Role].
 */
fun hasRole(
    role: Role
): SemanticsMatcher {
    val matcherDescription = "${SemanticsProperties.Role.name} contains '$role'"
    return SemanticsMatcher(description = matcherDescription) {
        val roleProperty = it.config.getOrNull(SemanticsProperties.Role) ?: false
        roleProperty == role
    }
}
