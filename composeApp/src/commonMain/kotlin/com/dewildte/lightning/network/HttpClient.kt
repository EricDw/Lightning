package com.dewildte.lightning.network

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

fun buildHttpClient(): HttpClient {
    return HttpClient {

        install(Auth) {
            basic {
                realm = "Access to the '/' path"
            }
        }

        install(ContentNegotiation) {
            json(
                json = Json {
                    encodeDefaults = true
                    isLenient = true
                    coerceInputValues = true
                    ignoreUnknownKeys = true
                },
            )
        }
        defaultRequest {
            host = LOCAL_HOST
            port = 8080
        }
    }
}
