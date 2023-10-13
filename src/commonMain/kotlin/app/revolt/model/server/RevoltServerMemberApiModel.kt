package app.revolt.model.server

import app.revolt.model.general.RevoltFileApiModel
import app.revolt.utils.RevoltApiConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RevoltServerMemberApiModel(
    @SerialName(RevoltApiConstants.ID)
    val id: ID,
    @SerialName("joined_at")
    val joinedAt: String,
    val nickname: String? = null,
    val avatar: RevoltFileApiModel? = null,
    val roles: List<String>? = null,
    val timeout: String? = null
) {

    @Serializable
    data class ID(val user: String, val server: String)
}
