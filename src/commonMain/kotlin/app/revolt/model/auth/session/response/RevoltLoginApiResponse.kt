package app.revolt.model.auth.session.response

import app.revolt.model.auth.RevoltMFAMethodApiType
import app.revolt.utils.RevoltApiConstants
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Polymorphic
@Serializable
@JsonClassDiscriminator("result")
sealed interface RevoltLoginApiResponse {

    @Serializable
    @SerialName("Success")
    data class Success(
        @SerialName(RevoltApiConstants.ID)
        val id: String,
        @SerialName("user_id")
        val userId: String,
        val token: String,
        val name: String,
        val subscription: Subscription? = null
    ) : RevoltLoginApiResponse {

        @Serializable
        data class Subscription(
            val endpoint: String,
            val p256dh: String,
            val auth: String
        )
    }

    @Serializable
    @SerialName("MFA")
    data class MFA(
        val ticket: String,
        val allowedMethods: RevoltMFAMethodApiType
    ) : RevoltLoginApiResponse

    @Serializable
    @SerialName("Disabled")
    data class Disabled(
        @SerialName("user_id")
        val userId: String
    ) : RevoltLoginApiResponse

}