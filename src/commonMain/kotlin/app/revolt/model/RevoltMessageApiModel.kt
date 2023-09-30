package app.revolt.model

import app.revolt.model.general.RevoltFileApiModel
import app.revolt.utils.RevoltApiConstants
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RevoltMessageApiModel(
    @SerialName(RevoltApiConstants.ID)
    val id: String,
    val nonce: String? = null,
    val channel: String,
    val author: String,
    val webhook: Webhook? = null,
    val content: String? = null,
    val system: System? = null,
    val attachments: List<RevoltFileApiModel>? = null,
    val edited: String? = null,
    val embeds: List<RevoltEmbedApiModel>? = null,
    val mentions: List<String>? = null,
    val replies: List<String>? = null,
    val reactions: Map<String, List<String>>? = null,
    val interactions: Interactions? = null,
    val masquerade: RevoltMasqueradeApiModel? = null
) {

    @Serializable
    data class Webhook(
        val name: String,
        val avatar: String? = null
    )

    @Serializable
    @Polymorphic
    sealed class System {

        @Serializable
        @SerialName("text")
        data class Text(val content: String) : System()

        @Serializable
        @SerialName("user_added")
        data class UserAdded(val id: String, val by: String) : System()

        @Serializable
        @SerialName("user_remove")
        data class UserRemoved(val id: String, val by: String) : System()

        @Serializable
        @SerialName("user_joined")
        data class UserJoined(val id: String) : System()

        @Serializable
        @SerialName("user_left")
        data class UserLeft(val id: String) : System()

        @Serializable
        @SerialName("user_kicked")
        data class UserKicked(val id: String) : System()

        @Serializable
        @SerialName("user_banned")
        data class UserBanned(val id: String) : System()

        @Serializable
        @SerialName("channel_renamed")
        data class ChannelRenamed(val name: String, val by: String) : System()

        @Serializable
        @SerialName("channel_description_changed")
        data class ChannelDescriptionChanged(val name: String, val by: String) : System()

        @Serializable
        @SerialName("channel_icon_changed")
        data class ChannelIconChanged(val name: String, val by: String) : System()

        @Serializable
        @SerialName("channel_ownership_changed")
        data class ChannelOwnershipChanged(val from: String, val to: String) : System()
    }

    @Serializable
    data class Interactions(
        val reactions: List<String>?,
        @SerialName("restrict_reactions")
        val restrictReactions: Boolean? = null
    )
}
