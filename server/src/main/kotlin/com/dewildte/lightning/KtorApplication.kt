package com.dewildte.lightning

import com.dewildte.lightning.auth.configureAuthentication
import com.dewildte.lightning.data.PostgresUserRepository
import com.dewildte.lightning.data.configureDatabases
import com.dewildte.lightning.dtos.transactions.data.InMemoryTransactionRepository
import com.dewildte.lightning.dtos.users.UserDTO
import com.dewildte.lightning.dtos.users.UserIdDTO
import com.dewildte.lightning.feature.onboarding.responses.LoginResponse
import com.dewildte.lightning.feature.transactions.data.TransactionMapper
import com.dewildte.lightning.feature.users.UserRepository
import com.dewildte.lightning.models.email.EmailAddress
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    val userRepository = PostgresUserRepository()

    val model = ServerLightningApplication(
        transactionRepository = InMemoryTransactionRepository(),
        userRepository = userRepository,
    )

    embeddedServer(
        factory = Netty,
        configure = { envConfig() },
        module = { module(model, userRepository = userRepository) }
    ).start(wait = true)
}

fun Application.module(
    model: LightningApplication,
    userRepository: UserRepository,
) {

    configureDatabases()
    configureAuthentication(
        userRepository = userRepository,
    )

    install(plugin = ContentNegotiation) {
        json()
    }

    install(CORS) {
        anyHost()
        allowHeader(HttpHeaders.ContentType)
    }

    routing {

        swaggerUI(path = "swagger", swaggerFile = "server/openapi/documentation.yaml")

        get("/") {
            val payload = """{}""".trimMargin()
            call.respondText(
                text = payload,
                contentType = ContentType.parse("application/json"),
                status = HttpStatusCode.OK,
            )
        }

        authenticate("auth-basic-hashed") {

            route("/onboarding") {
                post(path = "/login") {
                    try {

                        val principalName = call.principal<UserIdPrincipal>()?.name ?: ""

                        val email = EmailAddress(value = principalName)

                        val message = LightningApplication.Message.LoginWithEmailAndPassword(
                            email = email,
                        )

                        model.recieve(message)

                        val user = message.response.await()
                        val dto = UserDTO(
                            id = UserIdDTO(
                                value = user.id.value.toString()
                            )
                        )
                        val response = LoginResponse(
                            user = dto
                        )

                        call.respond(
                            status = HttpStatusCode.OK,
                            message = response,
                        )
                    } catch (error: Throwable) {
                        call.respond(status = HttpStatusCode.Unauthorized, message = error.toString())
                    }
                }

            }

            route("/finance") {
                get("/transactions") {
                    val mapper = TransactionMapper()
                    val message = LightningApplication.Message.RetrieveTransactions()
                    model.recieve(message)

                    try {
                        val transactions = message.response.await()
                            .map(mapper::mapTransactionToTransactionDto)
                        call.respond(
                            status = HttpStatusCode.OK,
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
}