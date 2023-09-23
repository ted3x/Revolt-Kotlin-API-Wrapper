package app.revolt.model.auth.session.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RevoltSessionEditApiRequest(
    @SerialName("friendly_name")
    val friendlyName: String
)