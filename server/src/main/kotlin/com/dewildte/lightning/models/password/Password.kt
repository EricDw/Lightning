package com.dewildte.lightning.models.password

class Password(
    val value: String
) {
    init {
        check(value.isNotBlank())
        // TODO: Complete validation
    }
}