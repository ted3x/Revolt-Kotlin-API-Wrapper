package app.revolt.model.auth.account.request

import kotlinx.serialization.Serializable

@Serializable
data class RevoltCreateAccountApiRequest(
    val email: String,
    val password: String,
    val invite: String?,
    val captcha: String?
)
