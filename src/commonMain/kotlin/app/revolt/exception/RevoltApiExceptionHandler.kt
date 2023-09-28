package app.revolt.exception

import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.*
import kotlin.enums.EnumEntries

/**
 * Handles exceptions from the Revolt API.
 */
class RevoltApiExceptionHandler {

    /**
     * Parses and processes the provided exception to determine the specific Revolt API error.
     * Throws a more specific [RevoltApiException] based on the error details contained in the response.
     *
     * @param json JSON parser utility.
     * @param cause The underlying exception.
     * @param request The originating HTTP request.
     * @throws RevoltApiException When a recognized error is found.
     */
    suspend fun handle(json: Json, cause: Throwable, request: HttpRequest) {
        request
        val clientException = cause as? ClientRequestException ?: throw RevoltApiException.Unknown(cause.message)
        val jsonElement = json.parseToJsonElement(clientException.response.body())

        checkRateLimitException(clientException.response, jsonElement)
        checkUnauthorizedException(clientException.response)

        val type = jsonElement.jsonObject[ERROR_FIELD_TYPE]?.jsonPrimitive?.content
        val errorType = RevoltErrorApiType.entries.firstOrNull { it.type == type } ?: throw RevoltApiException.Unknown(
            cause.message
        )

        throw when (errorType) {
            RevoltErrorApiType.GroupTooLarge, RevoltErrorApiType.TooManyEmbeds,
            RevoltErrorApiType.TooManyChannels, RevoltErrorApiType.TooManyReplies,
            RevoltErrorApiType.TooManyAttachments, RevoltErrorApiType.TooManyServers,
            RevoltErrorApiType.TooManyEmoji, RevoltErrorApiType.TooManyRoles ->
                countLimitException(jsonElement, errorType)

            RevoltErrorApiType.DatabaseError -> databaseErrorException(jsonElement)

            RevoltErrorApiType.MissingPermission ->
                permissionException(jsonElement, RevoltPermissionApiType.entries)

            RevoltErrorApiType.MissingUserPermission ->
                userPermissionException(jsonElement, RevoltUserPermissionApiType.entries)

            RevoltErrorApiType.FieldValidation -> fieldValidationException(jsonElement)

            else -> RevoltApiException.Default(errorType, getLocationValue(jsonElement))
        }
    }

    /**
     * Checks the [response] for a rate limit exception (HTTP 429) and throws a [RevoltApiException.RateLimitException]
     * if the response indicates too many requests.
     *
     * @param response The HTTP response to check for rate limit exceptions.
     * @param jsonElement The parsed JSON element from the response.
     *
     * @throws RevoltApiException.RateLimitException if the [response] status is `TooManyRequests`.
     * The exception will contain the retry-after duration provided by the server.
     */
    private fun checkRateLimitException(response: HttpResponse, jsonElement: JsonElement) {
        if (response.status == HttpStatusCode.TooManyRequests) {
            val retryAfter = jsonElement.jsonObject[ERROR_FIELD_RETRY_AFTER]?.jsonPrimitive?.int!!
            throw RevoltApiException.RateLimitException(retryAfter)
        }
    }

    /**
     * Checks the [response] for unauthorized exception (HTTP 401) and throws a [RevoltApiException.Unauthorized]
     *
     * @param response The HTTP response to check for unauthorized exceptions.
     *
     * @throws RevoltApiException.Unauthorized if the [response] status is `Unauthorized`.
     */
    private fun checkUnauthorizedException(response: HttpResponse) {
        if (response.status == HttpStatusCode.Unauthorized) {
            throw RevoltApiException.Unauthorized
        }
    }

    /**
     * Creates an exception indicating that a specific count limit was exceeded.
     *
     * @param jsonElement The parsed JSON element from the response.
     * @param errorType The identified error type.
     * @return A specialized [RevoltApiException.CountLimitException].
     */
    private fun countLimitException(jsonElement: JsonElement, errorType: RevoltErrorApiType): RevoltApiException {
        return RevoltApiException.CountLimitException(
            getMaxValue(jsonElement),
            errorType,
            getLocationValue(jsonElement)
        )
    }

    /**
     * Creates an exception related to database operations.
     *
     * @param jsonElement The parsed JSON element from the response.
     * @return A specialized [RevoltApiException.DatabaseError].
     */
    private fun databaseErrorException(jsonElement: JsonElement): RevoltApiException {
        val operation = jsonElement.jsonObject[ERROR_FIELD_OPERATION]?.jsonPrimitive?.content!!
        val with = jsonElement.jsonObject[ERROR_FIELD_WITH]?.jsonPrimitive?.content!!
        val collection = jsonElement.jsonObject[ERROR_FIELD_COLLECTION]?.jsonPrimitive?.contentOrNull
        return RevoltApiException.DatabaseError(operation, with, collection, getLocationValue(jsonElement))
    }

    /**
     * Creates an exception indicating missing permissions.
     *
     * @param jsonElement The parsed JSON element from the response.
     * @param permissions The enum entries of possible permissions.
     * @return A specialized [RevoltApiException.MissingPermission].
     */
    private fun permissionException(
        jsonElement: JsonElement,
        permissions: EnumEntries<RevoltPermissionApiType>
    ): RevoltApiException {
        val permission = jsonElement.jsonObject[ERROR_FIELD_PERMISSION]?.jsonPrimitive?.content!!
        val permissionType = permissions.first { it.type == permission }
        return RevoltApiException.MissingPermission(permissionType, getLocationValue(jsonElement))
    }

    /**
     * Creates an exception indicating missing user permissions.
     *
     * @param jsonElement The parsed JSON element from the response.
     * @param userPermissions The enum entries of possible user permissions.
     * @return A specialized [RevoltApiException.MissingUserPermission].
     */
    private fun userPermissionException(
        jsonElement: JsonElement,
        userPermissions: EnumEntries<RevoltUserPermissionApiType>
    ): RevoltApiException {
        val permission = jsonElement.jsonObject[ERROR_FIELD_PERMISSION]?.jsonPrimitive?.content!!
        val permissionType = userPermissions.first { it.type == permission }
        return RevoltApiException.MissingUserPermission(permissionType, getLocationValue(jsonElement))
    }

    /**
     * Creates an exception for field validation issues.
     *
     * @param jsonElement The parsed JSON element from the response.
     * @return A specialized [RevoltApiException.FieldValidation].
     */
    private fun fieldValidationException(jsonElement: JsonElement): RevoltApiException {
        val fieldError = jsonElement.jsonObject[ERROR_FIELD_ERROR]?.jsonPrimitive?.contentOrNull
        return RevoltApiException.FieldValidation(fieldError, getLocationValue(jsonElement))
    }


    /**
     * Retrieves the maximum value from the provided JSON element.
     *
     * @param jsonElement The parsed JSON element from the response.
     * @return The maximum value, if present.
     * @throws [kotlin.NullPointerException] if the field is absent in the JSON.
     */
    private fun getMaxValue(jsonElement: JsonElement) =
        jsonElement.jsonObject[ERROR_FIELD_MAX]?.jsonPrimitive?.int!!

    /**
     * Retrieves the location value from the provided JSON element.
     *
     * @param jsonElement The parsed JSON element from the response.
     * @return The location value, if present, or null.
     */
    private fun getLocationValue(jsonElement: JsonElement) =
        jsonElement.jsonObject[ERROR_FIELD_LOCATION]?.jsonPrimitive?.contentOrNull

    companion object {
        private const val ERROR_FIELD_OPERATION = "operation"
        private const val ERROR_FIELD_WITH = "with"
        private const val ERROR_FIELD_COLLECTION = "collection"
        private const val ERROR_FIELD_PERMISSION = "permission"
        private const val ERROR_FIELD_MAX = "max"
        private const val ERROR_FIELD_LOCATION = "location"
        private const val ERROR_FIELD_ERROR = "error"
        private const val ERROR_FIELD_TYPE = "type"
        private const val ERROR_FIELD_RETRY_AFTER = "retry_after"
    }
}
