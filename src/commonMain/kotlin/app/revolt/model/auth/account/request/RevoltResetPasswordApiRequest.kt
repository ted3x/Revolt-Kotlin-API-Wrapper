package app.revolt.model.auth.account.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RevoltResetPasswordApiRequest(
    val token: String,
    val password: String,
    @SerialName("remove_sessions")
    val removeSessions: Boolean = false
)
