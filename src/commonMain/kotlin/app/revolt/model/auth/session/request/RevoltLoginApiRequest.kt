package app.revolt.model.auth.session.request

import app.revolt.model.auth.RevoltMFAApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface RevoltLoginApiRequest {

    @Serializable
    data class Normal(
        val email: String,
        val password: String,
        @SerialName("friendly_name")
        val friendlyName: String? = null
    ) : RevoltLoginApiRequest

    @Serializable
    data class MFA(
        val mfaTicket: String,
        val mfaResponse: RevoltMFAApiModel,
        @SerialName("friendly_name")
        val friendlyName: String? = null
    ) {

    }

}