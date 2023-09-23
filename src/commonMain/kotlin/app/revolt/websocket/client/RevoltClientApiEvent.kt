package app.revolt.websocket.client

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Polymorphic
@Serializable
sealed class RevoltClientApiEvent {

    @Serializable
    @SerialName("Authenticate")
    data class Authenticate(val token: String) : RevoltClientApiEvent()

    @Serializable
    @SerialName("Ping")
    data class Ping(val data: Long) : RevoltClientApiEvent()

    @Serializable
    @SerialName("BeginTyping")
    data class BeginTyping(val channel: String) : RevoltClientApiEvent()

    @Serializable
    @SerialName("EndTyping")
    data class EndTyping(val channel: String) : RevoltClientApiEvent()
}