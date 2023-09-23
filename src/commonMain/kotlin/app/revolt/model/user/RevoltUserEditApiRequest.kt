package app.revolt.model.user

import app.revolt.model.general.RevoltFileApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RevoltUserEditApiRequest(
    @SerialName("display_name")
    val displayName: String?,
    val avatar: RevoltFileApiModel?,
    val status: RevoltUserStatusApiModel?,
    val profile: RevoltUserProfileApiModel?,
    val badges: Int?,
    val flags: Int?,
    val remove: List<FieldsUser>
) {

    @Serializable
    enum class FieldsUser {
        Avatar,
        StatusText,
        StatusPresence,
        ProfileContent,
        ProfileBackground,
        DisplayName
    }
}
