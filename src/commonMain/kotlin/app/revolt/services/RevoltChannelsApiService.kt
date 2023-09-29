package app.revolt.services

import app.revolt.services.channels.RevoltMessagingApiService
import io.ktor.client.*

class RevoltChannelsApiService(client: HttpClient) {

    val messaging: RevoltMessagingApiService by lazy {
        RevoltMessagingApiService(client)
    }
}