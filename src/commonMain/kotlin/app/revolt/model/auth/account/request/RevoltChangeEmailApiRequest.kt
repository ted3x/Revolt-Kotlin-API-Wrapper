package app.revolt.model.auth.account.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RevoltChangeEmailApiRequest(
    val email: String,
    @SerialName("current_password")
    val currentPassword: String
)