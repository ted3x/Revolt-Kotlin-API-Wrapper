package app.revolt.services.channels

import app.revolt.model.RevoltMessageApiModel
import app.revolt.model.channel.messaging.RevoltFetchMessagesRequestApiModel
import app.revolt.model.channel.messaging.RevoltFetchMessagesResponseApiModel
import app.revolt.utils.RevoltApiConstants
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class RevoltMessagingApiService(private val client: HttpClient) {

    suspend fun fetchMessages(request: RevoltFetchMessagesRequestApiModel): List<RevoltMessageApiModel> {
        return fetchMessageInternal(request, false)
    }

    suspend fun fetchMessagesWithUsers(request: RevoltFetchMessagesRequestApiModel): RevoltFetchMessagesResponseApiModel {
        return fetchMessageInternal(request, true)
    }

    suspend fun fetchMessage(channelId: String, messageId: String): RevoltMessageApiModel {
        return client.get(FETCH_MESSAGES_PATH.replace(RevoltApiConstants.TARGET, channelId) + "/$messageId") {
            contentType(ContentType.Application.Json)
        }.body()
    }

    private suspend fun <T> fetchMessageInternal(
        request: RevoltFetchMessagesRequestApiModel,
        includeUsers: Boolean
    ): T {
        val response = client.get(FETCH_MESSAGES_PATH.replace(RevoltApiConstants.TARGET, request.channelId)) {
            request.limit?.let { parameter("limit", it) }
            request.before?.let { parameter("before", it) }
            request.after?.let { parameter("after", it) }
            parameter("sort", request.sort.name)
            request.nearby?.let { parameter("nearby", it) }
            parameter("include_users", includeUsers)
            contentType(ContentType.Application.Json)
        }
        return if (includeUsers) response.body<RevoltFetchMessagesResponseApiModel>() as T
        else response.body<List<RevoltMessageApiModel>>() as T
    }

    companion object {
        private const val FETCH_MESSAGES_PATH = "channels/{target}/messages"
    }
}