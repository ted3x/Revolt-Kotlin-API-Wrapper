package app.revolt.model.auth.mfa.response

import app.revolt.utils.RevoltApiConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RevoltCreateMFATicketApiResponse(
    @SerialName(RevoltApiConstants.ID)
    val id: String,
    @SerialName("account_id")
    val accountId: String,
    val token: String,
    val validated: Boolean,
    val authorised: Boolean,
    @SerialName("last_totp_code")
    val lastTotpCode: String? = null
)