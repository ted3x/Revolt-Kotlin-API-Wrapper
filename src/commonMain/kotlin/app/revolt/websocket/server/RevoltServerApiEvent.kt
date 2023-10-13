package app.revolt.websocket.server

import app.revolt.model.channel.RevoltChannelApiModel
import app.revolt.model.general.RevoltEmojiApiModel
import app.revolt.model.server.RevoltServerApiModel
import app.revolt.model.user.RevoltUserApiModel
import app.revolt.model.websocket.RevoltUserUpdateApiModel
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Polymorphic
@Serializable
sealed interface RevoltServerApiEvent {

    @Serializable
    @SerialName("Ready")
    data class Ready(
        val users: List<RevoltUserApiModel>,
        val channels: List<RevoltChannelApiModel>,
        val servers: List<RevoltServerApiModel>,
        val emojis: List<RevoltEmojiApiModel>
    ) : RevoltServerApiEvent

    @Serializable
    @SerialName("Error")
    data class Error(val error: ErrorType) : RevoltServerApiEvent {

        @Serializable
        enum class ErrorType {
            LabelMe,
            InternalError,
            InvalidSession,
            OnboardingNotFinished,
            AlreadyAuthenticated
        }
    }

    @Serializable
    @SerialName("Authenticated")
    data object Authenticated : RevoltServerApiEvent

    @Serializable
    @SerialName("Bulk")
    data class Bulk(
        @SerialName("v")
        val events: List<RevoltServerApiEvent>
    ) : RevoltServerApiEvent

    @Serializable
    @SerialName("Pong")
    data class Pong(val data: Long) : RevoltServerApiEvent

    @Serializable
    @SerialName("UserUpdate")
    data class UserUpdate(
        val id: String,
        val data: RevoltUserUpdateApiModel,
        val clear: List<Field>
    ) : RevoltServerApiEvent {

        @Serializable
        enum class Field {
            ProfileContent,
            ProfileBackground,
            StatusText,
            Avatar
        }
    }
}