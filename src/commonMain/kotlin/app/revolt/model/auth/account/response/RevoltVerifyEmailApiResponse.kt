package app.revolt.model.auth.account.response

import app.revolt.utils.RevoltApiConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RevoltVerifyEmailApiResponse(
    val ticket: Ticket
) {

    @Serializable
    data class Ticket(
        @SerialName(RevoltApiConstants.ID)
        val id: String,
        @SerialName("account_id")
        val accountId: String,
        val token: String,
        val validated: Boolean,
        val authorised: Boolean,
        @SerialName("last_totp_code")
        val lastTotpCode: String?
    )
}