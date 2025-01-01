package com.dewildte.lightning.models.users

import com.dewildte.lightning.models.password.Password

class User(
    val id: UserId,
    val username: Username,
    val password: Password,
)