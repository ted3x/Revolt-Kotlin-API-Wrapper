package app.revolt.model.general

import app.revolt.utils.RevoltApiConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RevoltFileApiModel(
    @SerialName(RevoltApiConstants.ID)
    val id: String,
    val tag: String,
    val filename: String,
    val metadata: RevoltMetadataApiModel,
    @SerialName("content_type")
    val contentType: String,
    val size: Int,
    val deleted: Boolean? = null,
    val reported: Boolean? = null,
    @SerialName("message_id")
    val messageId: String? = null,
    @SerialName("user_id")
    val userId: String? = null,
    @SerialName("server_id")
    val serverId: String? = null,
    @SerialName("object_id")
    val objectId: String? = null,
)
