package app.revolt.model

import app.revolt.utils.RevoltApiConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RevoltMasqueradeApiModel(
    val name: String? = null,
    val avatar: String? = null,
    @SerialName(RevoltApiConstants.COLOUR)
    val color: String? = null
)