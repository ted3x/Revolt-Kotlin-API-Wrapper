package app.revolt.client

import io.ktor.client.*

interface ApiClientProvider {
    val client: HttpClient
}