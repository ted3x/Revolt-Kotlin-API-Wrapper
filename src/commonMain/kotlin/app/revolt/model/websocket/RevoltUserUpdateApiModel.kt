package app.revolt.model.websocket

import app.revolt.model.general.RevoltFileApiModel
import app.revolt.model.general.RevoltRelationshipStatusApiType
import app.revolt.model.user.RevoltUserApiModel
import app.revolt.model.user.RevoltUserProfileApiModel
import app.revolt.model.user.RevoltUserStatusApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RevoltUserUpdateApiModel(
    val username: String? = null,
    val discriminator: String? = null,
    @SerialName("display_name")
    val displayName: String? = null,
    val avatar: RevoltFileApiModel? = null,
    val relations: List<RevoltUserApiModel.Relationship>? = null,
    val badges: Int? = null,
    val status: RevoltUserStatusApiModel? = null,
    val profile: RevoltUserProfileApiModel? = null,
    val flags: Int? = null,
    val privileged: Boolean? = null,
    val bot: RevoltUserApiModel.Bot? = null,
    val relationship: RevoltRelationshipStatusApiType? = null,
    val online: Boolean? = null
)
