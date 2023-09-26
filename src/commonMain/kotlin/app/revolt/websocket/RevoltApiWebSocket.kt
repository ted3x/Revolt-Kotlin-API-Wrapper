package app.revolt.websocket

import app.revolt.websocket.client.RevoltClientApiEvent
import app.revolt.websocket.server.RevoltServerApiEvent
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class RevoltApiWebSocket(private val client: HttpClient, private val json: Json) {

    val incomingEvents = Channel<RevoltServerApiEvent>()

    private lateinit var socket: DefaultClientWebSocketSession

    suspend fun initialize(webSocketUrl: String) {
        socket = client.webSocketSession(webSocketUrl)
        start()
    }

    suspend fun sendEvent(event: RevoltClientApiEvent) {
        socket.sendSerialized(event)
        println(json.encodeToString(event))
    }

    private suspend fun start() = CoroutineScope(Dispatchers.Default).launch {
        socket.incoming.consumeAsFlow().buffer(Channel.UNLIMITED).collect { frame ->
            try {
                val decodedString = frame.data.decodeToString()
                val event = json.decodeFromString<RevoltServerApiEvent>(decodedString)
                println(event.toString())
                incomingEvents.send(event)
            } catch (e: Exception) {
                println("key is not added ${e.message}")
            }
        }
    }
}