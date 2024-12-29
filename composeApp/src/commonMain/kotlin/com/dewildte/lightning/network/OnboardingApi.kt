package com.dewildte.lightning.network

import com.dewildte.lightning.feature.onboarding.responses.LoginResponse
import com.dewildte.lightning.models.users.User
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class OnboardingApi(
    private val httpClient: HttpClient
) {

    companion object {
        private const val BASE_URL = "/onboarding"

    }

    suspend fun login(
        username: String,
        password: String,
    ): User {
        val response = httpClient.post("$BASE_URL/login") {
            basicAuth(
                username = username,
                password = password,
            )
        }

        val body = response.body<LoginResponse>()

        val mapper = UserDtoMapper()

        return mapper.mapToUser(body.user)

    }

}