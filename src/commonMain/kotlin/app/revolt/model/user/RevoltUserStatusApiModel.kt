package app.revolt.model.user

import kotlinx.serialization.Serializable

@Serializable
data class RevoltUserStatusApiModel(
    val text: String? = null,
    val presence: RevoltUserPresenceApiType? = null
)