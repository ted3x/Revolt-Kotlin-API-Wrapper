package app.revolt.model.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface RevoltMFAApiModel {
    @Serializable
    data class Password(val password: String) : RevoltMFAApiModel

    @Serializable
    data class RecoveryCode(
        @SerialName("recovery_code")
        val recoveryCode: String
    ) : RevoltMFAApiModel

    @Serializable
    data class TOTPCode(
        @SerialName("totp_code")
        val totpCode: String
    ) : RevoltMFAApiModel
}