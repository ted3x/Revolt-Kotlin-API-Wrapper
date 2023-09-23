package app.revolt.model.user

import app.revolt.model.general.RevoltFileApiModel
import kotlinx.serialization.Serializable

@Serializable
data class RevoltUserProfileApiModel(
    val content: String? = null,
    val background: RevoltFileApiModel? = null
)