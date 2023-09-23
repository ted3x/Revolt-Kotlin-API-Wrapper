package app.revolt.services

import app.revolt.model.RevoltConfigurationApiModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class RevoltCoreApiService(private val client: HttpClient) {

    /**
     * Fetches the configuration for the Revolt API.
     *
     * @return Returns a [RevoltConfigurationApiModel] containing the configuration details.
     * @throws [app.revolt.exception.RevoltApiException]
     */
    suspend fun fetchConfiguration(): RevoltConfigurationApiModel {
        return client.get(CORE_CONFIGURATION_PATH).body()
    }

    companion object {
        private const val CORE_CONFIGURATION_PATH = "/"
    }
}