package app.revolt.model.auth.onboarding.request

import kotlinx.serialization.Serializable

@Serializable
data class RevoltOnboardingCompleteApiRequest(
    val username: String
)