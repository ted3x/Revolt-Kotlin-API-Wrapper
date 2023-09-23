package app.revolt.model.auth.mfa.request

import kotlinx.serialization.Serializable

@Serializable
data class RevoltCreateMFATicketApiRequest(
    val password: String
)