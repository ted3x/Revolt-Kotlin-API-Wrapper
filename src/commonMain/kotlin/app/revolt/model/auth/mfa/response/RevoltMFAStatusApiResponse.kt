package app.revolt.model.auth.mfa.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RevoltMFAStatusApiResponse(
    @SerialName("email_otp")
    val emailOtp: Boolean,
    @SerialName("trusted_handover")
    val trustedHandover: Boolean,
    @SerialName("email_mfa")
    val emailMFA: Boolean,
    @SerialName("totp_mfa")
    val totpMFA: Boolean,
    @SerialName("security_key_mfa")
    val securityKeyMFA: Boolean,
    @SerialName("recovery_active")
    val recoveryActive: Boolean,
)