package app.revolt

import app.revolt.client.ApiClientProvider
import app.revolt.client.RevoltApiClientProvider
import app.revolt.services.auth.RevoltAuthApiService
import app.revolt.exception.RevoltApiExceptionHandler
import app.revolt.services.*
import app.revolt.websocket.RevoltApiWebSocket

class RevoltApi internal constructor(provider: ApiClientProvider) {

    private val client = provider.client

    val core by lazy { RevoltCoreApiService(client) }
    val auth by lazy { RevoltAuthApiService(client) }
    val admin by lazy { RevoltAdminApiService() }
    val bots by lazy { RevoltBotsApiService() }
    val channels by lazy { RevoltChannelsApiService() }
    val customisation by lazy { RevoltCustomisationApiService() }
    val invites by lazy { RevoltInvitesApiService() }
    val misc by lazy { RevoltMiscApiService() }
    val servers by lazy { RevoltServersApiService() }
    val users by lazy { RevoltUsersApiService(client) }

    val ws by lazy { RevoltApiWebSocket(client) }
}

fun revoltApi() = RevoltApi(
    provider = RevoltApiClientProvider(
        json = RevoltApiJsonFactory.create(),
        exceptionHandler = RevoltApiExceptionHandler()
    )
)