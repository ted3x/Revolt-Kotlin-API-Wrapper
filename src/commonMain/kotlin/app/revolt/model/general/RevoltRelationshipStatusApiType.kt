package app.revolt.model.general

import kotlinx.serialization.Serializable

@Serializable
enum class RevoltRelationshipStatusApiType {
    None,
    User,
    Friend,
    Outgoing,
    Incoming,
    Blocked,
    BlockedOther
}