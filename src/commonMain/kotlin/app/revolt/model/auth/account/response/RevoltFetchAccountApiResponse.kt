package app.revolt.model.auth.account.response

import app.revolt.utils.RevoltApiConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RevoltFetchAccountApiResponse(
    @SerialName(RevoltApiConstants.ID)
    val id: String,
    val email: String
)
