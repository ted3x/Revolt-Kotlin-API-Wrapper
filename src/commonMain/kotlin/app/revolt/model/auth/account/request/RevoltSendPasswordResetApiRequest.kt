package app.revolt.model.auth.account.request

import kotlinx.serialization.Serializable

@Serializable
data class RevoltSendPasswordResetApiRequest(
    val email: String,
    val captcha: String?
)