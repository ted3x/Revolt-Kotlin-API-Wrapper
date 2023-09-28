package app.revolt.services.auth

import app.revolt.model.auth.session.response.RevoltFetchSessionApiResponse
import app.revolt.model.auth.session.request.RevoltLoginApiRequest
import app.revolt.model.auth.session.response.RevoltLoginApiResponse
import app.revolt.model.auth.session.request.RevoltSessionEditApiRequest
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class RevoltSessionApiService(private val client: HttpClient) {

    suspend fun login(request: RevoltLoginApiRequest): RevoltLoginApiResponse = client.post(LOGIN_PATH) {
        contentType(ContentType.Application.Json)
        setBody(request)
    }.body()

    suspend fun logout() {
        client.post(LOGOUT_PATH)
    }

    suspend fun fetchSessions(): List<RevoltFetchSessionApiResponse> = client.get(ALL_SESSIONS_PATH).body()
    suspend fun deleteSessions() {
        client.delete(ALL_SESSIONS_PATH)
    }

    suspend fun deleteSession(sessionId: String) {
        client.delete(SESSIONS_PATH + sessionId)
    }

    suspend fun editSession(sessionId: String, request: RevoltSessionEditApiRequest) =
        client.patch(SESSIONS_PATH + sessionId) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }

    companion object {
        private const val LOGIN_PATH = "auth/session/login"
        private const val LOGOUT_PATH = "auth/session/logout"
        private const val ALL_SESSIONS_PATH = "auth/session/all"
        private const val SESSIONS_PATH = "auth/session/"
    }
}