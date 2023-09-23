package app.revolt.model.auth.account.request

import kotlinx.serialization.Serializable

@Serializable
data class RevoltResendVerificationApiRequest(
    val email: String,
    val captcha: String?
)
