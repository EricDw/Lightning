package com.dewildte.lightning.models.email

class EmailAddress(
    val value: String
) {
    init {
        check(value.isNotBlank())
        checkNotNull(value.single { it == '@' })
        // TODO: Complete the validation logic
    }
}