package app.revolt.exception

import kotlinx.serialization.Serializable

@Serializable
sealed class RevoltApiException(open val error: RevoltErrorApiType, open val location: String?) : Throwable(error.type) {
    data class Default(override val error: RevoltErrorApiType, override val location: String?) :
        RevoltApiException(error, location)

    data class CountLimitException(
        val max: Int,
        override val error: RevoltErrorApiType,
        override val location: String?
    ) : RevoltApiException(error, location)

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
