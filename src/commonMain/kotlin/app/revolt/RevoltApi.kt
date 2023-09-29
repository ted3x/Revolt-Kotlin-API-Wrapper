package app.revolt

import app.revolt.client.ApiClientProvider
import app.revolt.client.RevoltApiClientProvider
import app.revolt.services.auth.RevoltAuthApiService
import app.revolt.exception.RevoltApiExceptionHandler
import app.revolt.services.*
import app.revolt.websocket.RevoltApiWebSocket

class RevoltApi internal constructor(private val provider: ApiClientProvider) {

    private val client = provider.client

    val core by lazy { RevoltCoreApiService(client) }
    val auth by lazy { RevoltAuthApiService(client) }
    val admin by lazy { RevoltAdminApiService() }
    val bots by lazy { RevoltBotsApiService() }
    val channels by lazy { RevoltChannelsApiService(client) }
    val customisation by lazy { RevoltCustomisationApiService() }
    val invites by lazy { RevoltInvitesApiService() }
    val misc by lazy { RevoltMiscApiService() }
    val servers by lazy { RevoltServersApiService() }
    val users by lazy { RevoltUsersApiService(client) }
    val fileApi by lazy { RevoltFileApiService(client) }

    val ws by lazy { RevoltApiWebSocket(client, RevoltApiJsonFactory.create()) }

    fun updateToken(token: String) {
        provider.updateToken(token)
    }
}

fun revoltApi(config: RevoltApiConfig) = RevoltApi(
    provider = RevoltApiClientProvider(
        config = config,
        json = RevoltApiJsonFactory.create(),
        exceptionHandler = RevoltApiExceptionHandler()
    )
)