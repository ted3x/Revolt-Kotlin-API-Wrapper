package app.revolt

import kotlinx.serialization.Serializable

@Serializable
sealed class RevoltApiException(open val error: RevoltErrorApiType, open val location: String?) : Throwable(error.type) {
    data class Default(override val error: RevoltErrorApiType, override val location: String?) :
        RevoltApiException(error, location)

    data class GroupTooLarge(val max: Int, override val location: String?) :
        RevoltApiException(RevoltErrorApiType.GroupTooLarge, location)

    data class TooManyEmbeds(val max: Int, override val location: String?) :
        RevoltApiException(RevoltErrorApiType.TooManyEmbeds, location)

    data class TooManyChannels(val max: Int, override val location: String?) :
        RevoltApiException(RevoltErrorApiType.TooManyChannels, location)

    data class TooManyReplies(val max: Int, override val location: String?) :
        RevoltApiException(RevoltErrorApiType.TooManyReplies, location)

    data class TooManyAttachments(val max: Int, override val location: String?) :
        RevoltApiException(RevoltErrorApiType.TooManyAttachments, location)

    data class TooManyServers(val max: Int, override val location: String?) :
        RevoltApiException(RevoltErrorApiType.TooManyServers, location)

    data class TooManyEmoji(val max: Int, override val location: String?) :
        RevoltApiException(RevoltErrorApiType.TooManyEmoji, location)

    data class TooManyRoles(val max: Int, override val location: String?) :
        RevoltApiException(RevoltErrorApiType.TooManyRoles, location)

    data class DatabaseError(
        val operation: String,
        val with: String,
        val collection: String?,
        override val location: String?
    ) : RevoltApiException(RevoltErrorApiType.DatabaseError, location)

    data class MissingUserPermission(val permissionType: RevoltUserPermissionApiType, override val location: String?) :
        RevoltApiException(RevoltErrorApiType.MissingUserPermission, location)

    data class MissingPermission(val permissionType: RevoltPermissionApiType, override val location: String?) :
        RevoltApiException(RevoltErrorApiType.MissingPermission, location)

    data class FieldValidation(
        val fieldError: String?,
        override val location: String?
    ) : RevoltApiException(RevoltErrorApiType.FieldValidation, location)

    data object Unknown : RevoltApiException(RevoltErrorApiType.Unknown, null)
}
