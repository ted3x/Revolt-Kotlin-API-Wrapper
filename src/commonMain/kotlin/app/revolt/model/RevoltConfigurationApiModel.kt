package app.revolt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RevoltConfigurationApiModel(
    val revolt: String,
    val features: Features,
    val ws: String,
    val app: String,
    val vapid: String,
    val build: Build
) {

    @Serializable
    data class Features(
        val captcha: HCaptchaConfiguration,
        val email: Boolean,
        @SerialName("invite_only")
        val inviteOnly: Boolean,
        val autumn: GeneralServerConfiguration,
        val january: GeneralServerConfiguration,
        val voso: VoiceServerConfiguration
    ) {

        @Serializable
        data class HCaptchaConfiguration(val enabled: Boolean, val key: String)
        @Serializable
        data class GeneralServerConfiguration(val enabled: Boolean, val url: String)
        @Serializable
        data class VoiceServerConfiguration(val enabled: Boolean, val url: String, val ws: String)
    }

    @Serializable
    data class Build(
        @SerialName("commit_sha")
        val commitSHA: String,
        @SerialName("commit_timestamp")
        val commitTimestamp: String,
        val semver: String,
        @SerialName("origin_url")
        val originUrl: String,
        val timestamp: String
    )
}