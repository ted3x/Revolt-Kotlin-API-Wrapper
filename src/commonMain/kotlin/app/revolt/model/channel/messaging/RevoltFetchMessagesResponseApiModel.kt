package app.revolt.model.channel.messaging

import app.revolt.model.RevoltMessageApiModel
import app.revolt.model.server.RevoltServerMemberApiModel
import app.revolt.model.user.RevoltUserApiModel
import kotlinx.serialization.Serializable

@Serializable
data class RevoltFetchMessagesResponseApiModel(
    val messages: List<RevoltMessageApiModel>,
    val users: List<RevoltUserApiModel>,
    val members: List<RevoltServerMemberApiModel>? = null
)