package app.revolt.model.user

import app.revolt.model.general.RevoltFileApiModel
import app.revolt.model.general.RevoltRelationshipStatusApiType
import app.revolt.utils.RevoltApiConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RevoltUserApiModel(
    @SerialName(RevoltApiConstants.ID)
    val id: String,
    val username: String,
    val discriminator: String,
    @SerialName("display_name")
    val displayName: String? = null,
    val avatar: RevoltFileApiModel? = null,
    val relations: List<Relationship>? = null,
    val badges: Int? = null,
    val status: RevoltUserStatusApiModel? = null,
    val profile: RevoltUserProfileApiModel? = null,
    val flags: Int? = null,
    val privileged: Boolean? = null,
    val bot: Bot? = null,
    val relationship: RevoltRelationshipStatusApiType? = null,
    val online: Boolean? = null
) {

    @Serializable
    data class Relationship(
        @SerialName(RevoltApiConstants.ID)
        val id: String,
        val status: RevoltRelationshipStatusApiType
    )

    @Serializable
    data class Bot(val owner: String)
}
