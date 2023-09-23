package app.revolt.model.auth.onboarding.response

import kotlinx.serialization.Serializable

@Serializable
data class RevoltOnboardingStatusApiResponse(
    val onboarding: Boolean
)