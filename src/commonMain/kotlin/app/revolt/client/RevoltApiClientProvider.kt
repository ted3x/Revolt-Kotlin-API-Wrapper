package app.revolt.client

import app.revolt.exception.RevoltApiExceptionHandler
import app.revolt.utils.RevoltApiConstants
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

internal class RevoltApiClientProvider(
    private val json: Json,
    private val exceptionHandler: RevoltApiExceptionHandler,
    private val baseUrl: String = RevoltApiConstants.BASE_URL,
    private val logger: Logger = Logger.DEFAULT
): ApiClientProvider {

    override val client: HttpClient by lazy {
        HttpClient(defaultConfig)
    }

    private val defaultConfig: HttpClientConfig<*>.() -> Unit = {
        install(ContentNegotiation) {
            json(json)
        }
        install(Logging) {
            logger = this@RevoltApiClientProvider.logger
            level = LogLevel.ALL
        }

        install(WebSockets) {
            contentConverter = KotlinxWebsocketSerializationConverter(Json)
            maxFrameSize = Long.MAX_VALUE
        }

        defaultRequest {
            host = baseUrl
            url { protocol = URLProtocol.HTTPS }
        }

        expectSuccess = true

        HttpResponseValidator {
            handleResponseExceptionWithRequest { cause, request ->
                exceptionHandler.handle(json, cause, request)
            }
        }
    }
}