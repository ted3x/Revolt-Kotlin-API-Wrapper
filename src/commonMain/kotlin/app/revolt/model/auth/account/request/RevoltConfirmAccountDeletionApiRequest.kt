package app.revolt.model.auth.account.request

import kotlinx.serialization.Serializable

@Serializable
data class RevoltConfirmAccountDeletionApiRequest(
    val token: String
)
