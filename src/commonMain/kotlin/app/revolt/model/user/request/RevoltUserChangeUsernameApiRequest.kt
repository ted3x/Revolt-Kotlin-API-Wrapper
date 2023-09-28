package app.revolt.model.user.request

import kotlinx.serialization.Serializable

/**
 * @param username new username, length [2-32]
 * @param password current password
 */
@Serializable
class RevoltUserChangeUsernameApiRequest(
    val username: String,
    val password: String
)