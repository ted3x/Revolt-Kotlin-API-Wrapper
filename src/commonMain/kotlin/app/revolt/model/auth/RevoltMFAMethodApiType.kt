package app.revolt.model.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class RevoltMFAMethodApiType {
    @SerialName("Password")
    Password,

    @SerialName("Recovery")
    Recovery,

    @SerialName("Totp")
    Totp
}