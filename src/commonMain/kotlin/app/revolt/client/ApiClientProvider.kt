package app.revolt.client

import io.ktor.client.*

interface ApiClientProvider {
    val client: HttpClient

    fun updateToken(token: String)
}