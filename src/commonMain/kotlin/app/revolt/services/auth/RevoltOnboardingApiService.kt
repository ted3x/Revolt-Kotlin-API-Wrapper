package app.revolt.services.auth

import app.revolt.model.auth.onboarding.request.RevoltOnboardingCompleteApiRequest
import app.revolt.model.auth.onboarding.response.RevoltOnboardingStatusApiResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class RevoltOnboardingApiService(private val client: HttpClient) {

    suspend fun status(): RevoltOnboardingStatusApiResponse = client.get(ONBOARDING_STATUS_PATH).body()

    suspend fun complete(request: RevoltOnboardingCompleteApiRequest) = client.post(ONBOARDING_COMPLETE_PATH) {
        contentType(ContentType.Application.Json)
        setBody(request)
    }

    companion object {
        private const val ONBOARDING_STATUS_PATH = "onboard/hello"
        private const val ONBOARDING_COMPLETE_PATH = "onboard/complete"
    }
}