package app.revolt.model.server

import app.revolt.model.general.RevoltOverrideFieldApiModel
import app.revolt.utils.RevoltApiConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RevoltServerRoleApiModel(
    val name: String,
    val permissions: RevoltOverrideFieldApiModel,
    @SerialName(RevoltApiConstants.COLOUR)
    val color: String? = null,
    val hoist: Boolean? = null,
    val rank: Int = 0
)
