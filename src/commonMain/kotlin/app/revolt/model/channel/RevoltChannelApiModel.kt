package app.revolt.model.channel

import app.revolt.model.general.RevoltFileApiModel
import app.revolt.model.general.RevoltOverrideFieldApiModel
import app.revolt.utils.RevoltApiConstants
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@Polymorphic
@JsonClassDiscriminator("channel_type")
sealed interface RevoltChannelApiModel {

    @Serializable
    @SerialName("SavedMessages")
    data class SavedMessages(
        @SerialName(RevoltApiConstants.ID)
        val id: String,
        val user: String,
    ): RevoltChannelApiModel

    @Serializable
    @SerialName("DirectMessage")
    data class DirectMessage(
        @SerialName(RevoltApiConstants.ID)
        val id: String,
        val active: Boolean,
        val recipients: List<String>,
        @SerialName("last_message_id")
        val lastMessageId: String? = null
    ): RevoltChannelApiModel

    @Serializable
    @SerialName("Group")
    data class Group(
        @SerialName(RevoltApiConstants.ID)
        val id: String,
        val name: String,
        val owner: String,
        val description: String? = null,
        val recipients: List<String>,
        val icon: RevoltFileApiModel? = null,
        @SerialName("last_message_id")
        val lastMessageId: String? = null,
        val permissions: Int? = null,
        val nsfw: Boolean? = null
    ): RevoltChannelApiModel

    @Serializable
    @SerialName("TextChannel")
    data class TextChannel(
        @SerialName(RevoltApiConstants.ID)
        val id: String,
        val server: String,
        val name: String,
        val description: String? = null,
        val icon: RevoltFileApiModel? = null,
        @SerialName("last_message_id")
        val lastMessageId: String? = null,
        val defaultPermissions: RevoltOverrideFieldApiModel? = null,
        val rolePermissions: Map<String, RevoltOverrideFieldApiModel>? = null,
        val nsfw: Boolean? = null
    ): RevoltChannelApiModel

    @Serializable
    @SerialName("VoiceChannel")
    data class VoiceChannel(
        @SerialName(RevoltApiConstants.ID)
        val id: String,
        val server: String,
        val name: String,
        val description: String? = null,
        val icon: RevoltFileApiModel? = null,
        val defaultPermissions: RevoltOverrideFieldApiModel? = null,
        val rolePermissions: Map<String, RevoltOverrideFieldApiModel>? = null,
        val nsfw: Boolean? = null
    ): RevoltChannelApiModel
}

