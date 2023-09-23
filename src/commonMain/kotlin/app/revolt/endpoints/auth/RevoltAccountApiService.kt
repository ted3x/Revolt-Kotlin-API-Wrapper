package app.revolt.endpoints.auth

import app.revolt.model.auth.account.request.*
import app.revolt.model.auth.account.response.RevoltFetchAccountApiResponse
import app.revolt.model.auth.account.response.RevoltVerifyEmailApiResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class RevoltAccountApiService(private val client: HttpClient) {

    suspend fun createAccount(request: RevoltCreateAccountApiRequest) {
        client.post(CREATE_ACCOUNT_PATH) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    suspend fun resendVerification(request: RevoltResendVerificationApiRequest) {
        client.post(RESEND_VERIFICATION_PATH) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    suspend fun confirmAccountDeletion(request: RevoltConfirmAccountDeletionApiRequest) {
        client.put(CONFIRM_ACCOUNT_DELETION_PATH) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    suspend fun deleteAccount() {
        client.post(DELETE_ACCOUNT_PATH)
    }

    suspend fun fetchAccount(): RevoltFetchAccountApiResponse = client.get(FETCH_ACCOUNT_PATH).body()

    suspend fun disableAccount() {
        client.post(DISABLE_ACCOUNT_PATH)
    }

    suspend fun changePassword(request: RevoltChangePasswordApiRequest) {
        client.patch(CHANGE_PASSWORD_PATH) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    suspend fun changeEmail(request: RevoltChangeEmailApiRequest) {
        client.patch(CHANGE_EMAIL_PATH) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    suspend fun verifyEmail(request: RevoltVerifyEmailApiRequest): RevoltVerifyEmailApiResponse =
        client.post(VERIFY_EMAIL_PATH + request.code) {
            contentType(ContentType.Application.Json)
        }.body()

    suspend fun sendPasswordReset(request: RevoltSendPasswordResetApiRequest) {
        client.post(SEND_PASSWORD_RESET_PATH) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    suspend fun resetPassword(request: RevoltResetPasswordApiRequest) {
        client.patch(RESET_PASSWORD_PATH) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    companion object {
        internal const val CREATE_ACCOUNT_PATH = "auth/account/create"
        internal const val RESEND_VERIFICATION_PATH = "auth/account/reverify"
        internal const val CONFIRM_ACCOUNT_DELETION_PATH = "auth/account/delete"
        internal const val DELETE_ACCOUNT_PATH = "auth/account/delete"
        internal const val FETCH_ACCOUNT_PATH = "auth/account"
        internal const val DISABLE_ACCOUNT_PATH = "auth/account/disable"
        internal const val CHANGE_PASSWORD_PATH = "auth/account/change/password"
        internal const val CHANGE_EMAIL_PATH = "auth/account/change/email"
        internal const val VERIFY_EMAIL_PATH = "auth/account/verify/"
        internal const val SEND_PASSWORD_RESET_PATH = "auth/account/reset_password"
        internal const val RESET_PASSWORD_PATH = "auth/account/reset_password"
    }
}