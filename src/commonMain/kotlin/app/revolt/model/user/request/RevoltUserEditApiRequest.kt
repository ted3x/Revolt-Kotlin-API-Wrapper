package app.revolt.model.user.request

import app.revolt.model.user.RevoltUserStatusApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RevoltUserEditApiRequest(
    @SerialName("display_name")
    val displayName: String? = null,
    @SerialName("avatar")
    val avatarId: String? = null,
    val status: RevoltUserStatusApiModel? = null,
    val profile: Profile? = null,
    val badges: Int? = null,
    val flags: Int? = null,
    val remove: List<FieldsUser>? = null
) {

    @Serializable
    data class Profile(val content: String? = null, val background: String? = null)

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
