package app.revolt.services.auth

import app.revolt.TestUtils
import app.revolt.services.auth.RevoltAccountApiService.Companion.CONFIRM_ACCOUNT_DELETION_PATH
import app.revolt.services.auth.RevoltAccountApiService.Companion.CREATE_ACCOUNT_PATH
import app.revolt.services.auth.RevoltAccountApiService.Companion.RESEND_VERIFICATION_PATH
import app.revolt.model.auth.account.request.RevoltConfirmAccountDeletionApiRequest
import app.revolt.model.auth.account.request.RevoltCreateAccountApiRequest
import app.revolt.model.auth.account.request.RevoltResendVerificationApiRequest
import kotlinx.coroutines.test.runTest
import kotlin.test.Test


class RevoltAccountApiTest {

    private val testClient = mockHttpClient(
        mapOf(
            CREATE_ACCOUNT_PATH to TestUtils.EMPTY_JSON,
            RESEND_VERIFICATION_PATH to TestUtils.EMPTY_JSON,
            CONFIRM_ACCOUNT_DELETION_PATH to TestUtils.EMPTY_JSON
        )
    )
    private val testApi = RevoltAccountApiService(testClient)

    @Test
    fun createAccount() = runTest {
        val request = RevoltCreateAccountApiRequest("email", "password", "invite", "captcha")
        testApi.createAccount(request)
    }

    @Test
    fun resendVerification() = runTest {
        val request = RevoltResendVerificationApiRequest("email", "captcha")
        testApi.resendVerification(request)
    }

    @Test
    fun confirmAccountDeletion() = runTest {
        val request = RevoltConfirmAccountDeletionApiRequest("token")
        testApi.confirmAccountDeletion(request)
    }

    @Test
    fun deleteAccount() = runTest {
        testApi.deleteAccount()
    }
}