package app.revolt.model.user

import kotlinx.serialization.Serializable

@Serializable
data class RevoltUserFlagsApiResponse(val flags: Int)
