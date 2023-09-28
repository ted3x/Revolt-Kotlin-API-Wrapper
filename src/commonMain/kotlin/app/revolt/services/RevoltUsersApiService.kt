package app.revolt.services

import app.revolt.exception.RevoltApiException
import app.revolt.model.user.*
import app.revolt.model.user.request.RevoltUserChangeUsernameApiRequest
import app.revolt.model.user.request.RevoltUserEditApiRequest
import app.revolt.model.user.response.RevoltUserFlagsApiResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class RevoltUsersApiService(private val client: HttpClient) {

    suspend fun fetchSelf(): RevoltUserApiModel {
        val userModel = client.get(USERS_SELF_PATH).body<RevoltUserApiModel>()

        // FetchSelf does not fetch Profile,idk why so for now it fetch profile as well in separate call
        val profile = try {
            fetchUserProfile(userModel.id)
        } catch (e: RevoltApiException) {
            null
        }
        return userModel.copy(profile = profile)
    }

    suspend fun fetchUser(userId: String): RevoltUserApiModel {
        val userModel = client.get(USERS_PATH + userId).body<RevoltUserApiModel>()
        val profile = try {
            fetchUserProfile(userModel.id)
        } catch (e: RevoltApiException) {
            null
        }
        return userModel.copy(profile = profile)
    }

    suspend fun editUser(request: RevoltUserEditApiRequest): RevoltUserApiModel =
        client.patch(USERS_SELF_PATH) {
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