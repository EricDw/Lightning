package com.dewildte.lightning

import com.dewildte.lightning.finance.InMemoryTransactionRepository
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(
        factory = Netty,
        configure = { envConfig() },
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {

    install(plugin = ContentNegotiation) {
        json()
    }

    val transactionRepository = InMemoryTransactionRepository()

    routing {
        get("/") {
            val greeting = Greeting().greet()
            val payload = """{"greeting"="$greeting"}""".trimMargin()
            call.respondText(
                text = payload,
                contentType = ContentType.parse("application/json"),
                status = HttpStatusCode.OK,
            )
        }

        route("/finance") {
            get("/transactions") {
                val transactions = transactionRepository.retrieveAllTransactions()
                call.respond(
                    message = transactions,
                )
            }
        }
    }
}