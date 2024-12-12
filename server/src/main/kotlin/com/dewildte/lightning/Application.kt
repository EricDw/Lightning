package com.dewildte.lightning

import com.dewildte.lightning.application.api.LightningApplication
import com.dewildte.lightning.feature.transactions.InMemoryTransactionRepository
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    val model = ServerLightningApplication()

    embeddedServer(
        factory = Netty,
        configure = { envConfig() },
        module = { module(model) }
    ).start(wait = true)
}

fun Application.module(
    model: LightningApplication
) {

    install(plugin = ContentNegotiation) {
        json()
    }

    routing {
        get("/") {
            val payload = """{}""".trimMargin()
            call.respondText(
                text = payload,
                contentType = ContentType.parse("application/json"),
                status = HttpStatusCode.OK,
            )
        }

        route("/finance") {
            get("/transactions") {
                val message = LightningApplication.Message.RetrieveTransactions()
                model.recieve(message)
                try {
                    val transactions = message.response.await()
                    call.respond(
                        status = HttpStatusCode.Found,
                        message = transactions,
                    )
                } catch (error: Throwable) {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = error,
                    )
                }

            }
        }
    }
}