package app.revolt.model.user.response

import kotlinx.serialization.Serializable

@Serializable
data class RevoltUserFlagsApiResponse(val flags: Int)
