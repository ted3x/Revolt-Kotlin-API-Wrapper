package app.revolt

import app.revolt.endpoints.*
import app.revolt.endpoints.auth.RevoltAuthApiService
import app.revolt.websocket.RevoltApiWebSocket
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.*

class RevoltApi {

    private val defaultConfig: HttpClientConfig<*>.() -> Unit = {
        val json = RevoltApiJsonFactory.create()
        install(ContentNegotiation) {
            json(json)
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }


        install(WebSockets) {
            contentConverter = KotlinxWebsocketSerializationConverter(Json)
            maxFrameSize = Long.MAX_VALUE
        }

        defaultRequest {
            host = BASE_URL
            url {
                protocol = URLProtocol.HTTPS
            }
            headers.append("X-Session-Token", "KKx3FtQQlg4z5h3O7tzUZ9yyxD12r2ZWkLO47JtkD3WN0sqPHXOwQV3Fch6b2IzV")
        }

        expectSuccess = true

        HttpResponseValidator {
            handleResponseExceptionWithRequest { cause, request ->
                val clientException = cause as? ClientRequestException ?: throw RevoltApiException.Unknown
                val jsonElement = json.parseToJsonElement(clientException.response.body())
                val type = jsonElement.jsonObject["type"]?.jsonPrimitive?.content
                RevoltErrorApiType.entries.firstOrNull { it.type == type }?.let { errorType ->
                    fun getMaxValue() = jsonElement.jsonObject["max"]?.jsonPrimitive?.int!!
                    fun getLocationValue() = jsonElement.jsonObject["location"]?.jsonPrimitive?.contentOrNull
                    throw when (errorType) {
                        RevoltErrorApiType.GroupTooLarge -> RevoltApiException.GroupTooLarge(
                            getMaxValue(),
                            getLocationValue()
                        )

                        RevoltErrorApiType.TooManyEmbeds -> RevoltApiException.TooManyEmbeds(
                            getMaxValue(),
                            getLocationValue()
                        )

                        RevoltErrorApiType.TooManyChannels -> RevoltApiException.TooManyChannels(
                            getMaxValue(),
                            getLocationValue()
                        )

                        RevoltErrorApiType.TooManyReplies -> RevoltApiException.TooManyReplies(
                            getMaxValue(),
                            getLocationValue()
                        )

                        RevoltErrorApiType.TooManyAttachments -> RevoltApiException.TooManyAttachments(
                            getMaxValue(),
                            getLocationValue()
                        )

                        RevoltErrorApiType.TooManyServers -> RevoltApiException.TooManyServers(
                            getMaxValue(),
                            getLocationValue()
                        )

                        RevoltErrorApiType.TooManyEmoji -> RevoltApiException.TooManyEmoji(getMaxValue(), getLocationValue())
                        RevoltErrorApiType.TooManyRoles -> RevoltApiException.TooManyRoles(getMaxValue(), getLocationValue())
                        RevoltErrorApiType.DatabaseError -> {
                            val operation = jsonElement.jsonObject["operation"]?.jsonPrimitive?.content!!
                            val with = jsonElement.jsonObject["with"]?.jsonPrimitive?.content!!
                            val collection = jsonElement.jsonObject["collection"]?.jsonPrimitive?.contentOrNull
                            RevoltApiException.DatabaseError(operation, with, collection, getLocationValue())
                        }

                        RevoltErrorApiType.MissingPermission -> {
                            val permission = jsonElement.jsonObject["permission"]?.jsonPrimitive?.content!!
                            val permissionType = RevoltPermissionApiType.entries.first { it.type == permission }
                            RevoltApiException.MissingPermission(permissionType, getLocationValue())
                        }

                        RevoltErrorApiType.MissingUserPermission -> {
                            val permission = jsonElement.jsonObject["permission"]?.jsonPrimitive?.content!!
                            val permissionType = RevoltUserPermissionApiType.entries.first { it.type == permission }
                            RevoltApiException.MissingUserPermission(permissionType, getLocationValue())
                        }

                        RevoltErrorApiType.FieldValidation -> {
                            val fieldError = jsonElement.jsonObject["error"]?.jsonPrimitive?.contentOrNull
                            RevoltApiException.FieldValidation(fieldError, getLocationValue())
                        }

                        else -> RevoltApiException.Default(errorType, getLocationValue())
                    }
                }
            }
        }
    }

    private val client: HttpClient = HttpClient(defaultConfig)

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

    companion object {
        internal const val BASE_URL = "api.revolt.chat/"
    }
}