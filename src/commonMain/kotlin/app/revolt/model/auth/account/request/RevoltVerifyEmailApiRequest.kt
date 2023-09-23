package app.revolt.model.auth.account.request

import kotlinx.serialization.Serializable

@Serializable
data class RevoltVerifyEmailApiRequest(val code: String)