package app.revolt.model.auth.account.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RevoltChangePasswordApiRequest(
    val password: String,
    @SerialName("current_password")
    val currentPassword: String
)