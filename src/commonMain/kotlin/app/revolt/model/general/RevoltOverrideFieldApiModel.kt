package app.revolt.model.general

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RevoltOverrideFieldApiModel(
    @SerialName("a")
    val allowed: Int,
    @SerialName("d")
    val disallowed: Int
)