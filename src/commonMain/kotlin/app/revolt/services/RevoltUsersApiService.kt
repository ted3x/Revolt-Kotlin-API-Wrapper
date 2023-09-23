package app.revolt.services

import app.revolt.model.user.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class RevoltUsersApiService(private val client: HttpClient) {

    suspend fun fetchSelf(): RevoltUserApiModel = client.get(USERS_SELF_PATH).body()
    suspend fun fetchUser(userId: String): RevoltUserApiModel = client.get(USERS_PATH + userId).body()
    suspend fun editUser(userId: String, request: RevoltUserEditApiRequest): RevoltUserApiModel =
        client.patch(USERS_PATH + userId) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()

    suspend fun fetchUserFlags(userId: String): RevoltUserFlagsApiResponse =
        client.get(FETCH_USER_FLAGS_PATH.replace("%s", userId)).body()

    suspend fun changeUsername(request: RevoltUserChangeUsernameApiRequest): RevoltUserApiModel =
        client.patch(USERS_USERNAME_CHANGE_PATH) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()

    suspend fun fetchUserDefaultAvatar(userId: String): ByteArray =
        client.get(FETCH_USER_DEFAULT_AVATAR_PATH.replace("%s", userId)).body()

    suspend fun fetchUserProfile(userId: String): RevoltUserProfileApiModel =
        client.get(FETCH_USER_PROFILE_PATH.replace("%s", userId)).body()

    companion object {
        private const val USERS_SELF_PATH = "users/@me"
        private const val USERS_USERNAME_CHANGE_PATH = "$USERS_SELF_PATH/username"
        private const val USERS_PATH = "users/"
        private const val FETCH_USER_FLAGS_PATH = "users/%s/flags"
        private const val FETCH_USER_DEFAULT_AVATAR_PATH = "users/%s/default_avatar"
        private const val FETCH_USER_PROFILE_PATH = "users/%s/profile"
    }
}