package app.revolt.services.channels

import app.revolt.model.RevoltMessageApiModel
import app.revolt.model.channel.messaging.RevoltFetchMessagesRequestApiModel
import app.revolt.utils.RevoltApiConstants
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class RevoltMessagingApiService(private val client: HttpClient) {

    suspend fun fetchMessages(request: RevoltFetchMessagesRequestApiModel): List<RevoltMessageApiModel> {
        return client.get(FETCH_MESSAGES_PATH.replace(RevoltApiConstants.TARGET, request.channelId)) {
            request.limit?.let { parameter("limit", it) }
            request.before?.let { parameter("before", it) }
            request.after?.let { parameter("after", it) }
            parameter("sort", request.sort.name)
            request.nearby?.let { parameter("nearby", it) }
            request.includeUsers?.let { parameter("includeUsers", it) }
            contentType(ContentType.Application.Json)
        }.body()
    }

    companion object {
        private const val FETCH_MESSAGES_PATH = "channels/{target}/messages"
    }
}