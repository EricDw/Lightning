package com.dewildte.lightning.data

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabases() {

    Database.connect(
        "jdbc:postgresql://localhost:5432/postgres",
        user = "ericdewildt",
        password = "731946825!"
    )

}