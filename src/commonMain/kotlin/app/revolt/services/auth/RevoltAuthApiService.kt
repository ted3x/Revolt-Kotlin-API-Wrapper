package app.revolt.services.auth

import io.ktor.client.*

class RevoltAuthApiService(private val client: HttpClient) {

    val account by lazy { RevoltAccountApiService(client) }
    val session by lazy { RevoltSessionApiService(client) }
    val mfa by lazy { RevoltMFAApiService(client) }
    val onboarding by lazy { RevoltOnboardingApiService(client) }
}