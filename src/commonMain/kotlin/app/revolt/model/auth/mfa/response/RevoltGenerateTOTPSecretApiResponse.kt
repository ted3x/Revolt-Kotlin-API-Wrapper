package app.revolt.model.auth.mfa.response

import kotlinx.serialization.Serializable

@Serializable
data class RevoltGenerateTOTPSecretApiResponse(
    val secret: String
)