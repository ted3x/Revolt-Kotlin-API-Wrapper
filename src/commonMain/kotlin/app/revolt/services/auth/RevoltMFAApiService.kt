package app.revolt.services.auth

import app.revolt.model.auth.RevoltMFAApiModel
import app.revolt.model.auth.RevoltMFAMethodApiType
import app.revolt.model.auth.mfa.request.RevoltCreateMFATicketApiRequest
import app.revolt.model.auth.mfa.response.RevoltCreateMFATicketApiResponse
import app.revolt.model.auth.mfa.response.RevoltGenerateTOTPSecretApiResponse
import app.revolt.model.auth.mfa.response.RevoltMFAStatusApiResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class RevoltMFAApiService(private val client: HttpClient) {

    suspend fun createMFATicket(request: RevoltCreateMFATicketApiRequest): RevoltCreateMFATicketApiResponse =
        client.post(CREATE_MFA_TICKET_PATH) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()

    suspend fun mfaStatus(): RevoltMFAStatusApiResponse = client.get(MFA_PATH).body()

    suspend fun fetchRecoveryCodes(): Array<String> = client.post(MFA_RECOVERY_PATH).body()
    suspend fun generateRecoveryCodes(): Array<String> = client.patch(MFA_RECOVERY_PATH).body()
    suspend fun getMFAMethods(): Array<RevoltMFAMethodApiType> = client.patch(MFA_METHODS_PATH).body()
    suspend fun enableTOTP2FA(request: RevoltMFAApiModel) = client.put(TOTP_PATH) {
        contentType(ContentType.Application.Json)
        setBody(request)
    }

    suspend fun generateTOTPSecret(): RevoltGenerateTOTPSecretApiResponse = client.post(TOTP_PATH).body()
    suspend fun disableTOTP2FA() {
        client.delete(TOTP_PATH)
    }

    companion object {
        private const val CREATE_MFA_TICKET_PATH = "auth/mfa/ticket"
        private const val MFA_PATH = "auth/mfa"
        private const val MFA_RECOVERY_PATH = "auth/mfa/recovery"
        private const val MFA_METHODS_PATH = "auth/mfa/methods"
        private const val TOTP_PATH = "auth/mfa/totp"
    }
}