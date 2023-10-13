package app.revolt.model.general

import app.revolt.utils.RevoltApiConstants
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RevoltEmojiApiModel(
    @SerialName(RevoltApiConstants.ID)
    val id: String,
    val parent: Parent,
    @SerialName("creator_id")
    val creatorId: String,
    val name: String,
    val animated: Boolean? = null,
    val nsfw: Boolean? = null
) {

    @Serializable
    @Polymorphic
    sealed interface Parent {

        @Serializable
        @SerialName("Server")
        data class Server(val id: String): Parent

        @Serializable
        @SerialName("Detached")
        data object Detached: Parent
    }
}
