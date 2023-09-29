package app.revolt.model.channel.messaging

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RevoltFetchMessagesRequestApiModel(
    val channelId: String,
    val limit: Int? = null,
    val before: String? = null,
    val after: String? = null,
    val sort: Sort = Sort.Latest,
    val nearby: String? = null,
    @SerialName("include_users")
    val includeUsers: Boolean? = null
) {

    @Serializable
    enum class Sort {
        Relevance,
        Latest,
        Oldest
    }
}