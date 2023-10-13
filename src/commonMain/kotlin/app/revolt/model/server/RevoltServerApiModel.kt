package app.revolt.model.server

import app.revolt.model.general.RevoltFileApiModel
import app.revolt.utils.RevoltApiConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RevoltServerApiModel(
    @SerialName(RevoltApiConstants.ID)
    val id: String,
    val owner: String,
    val name: String,
    val description: String? = null,
    val channels: List<String>,
    val categories: List<Category>? = null,
    val systemMessages: SystemMessages? = null,
    val roles: Map<String, RevoltServerRoleApiModel>? = null,
    val defaultPermissions: Int,
    val icon: RevoltFileApiModel? = null,
    val banner: RevoltFileApiModel? = null,
    val flags: Int? = null,
    val nsfw: Boolean? = null,
    val analytics: Boolean? = null,
    val discoverable: Boolean? = null
) {
    @Serializable
    data class Category(
        val id: String,
        val title: String,
        val channels: List<String>
    )

    @Serializable
    data class SystemMessages(
        @SerialName("user_joined")
        val userJoined: String,
        @SerialName("user_left")
        val userLeft: String,
        @SerialName("user_kicked")
        val userKicked: String,
        @SerialName("user_banned")
        val userBanned: String,
    )
}
