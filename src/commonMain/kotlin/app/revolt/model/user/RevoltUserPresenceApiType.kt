package app.revolt.model.user

import kotlinx.serialization.Serializable

@Serializable
enum class RevoltUserPresenceApiType {
    Online,
    Idle,
    Focus,
    Busy,
    Invisible
}