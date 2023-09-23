package app.revolt.endpoints

import app.revolt.model.RevoltConfigurationApiModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class RevoltCoreApiService(private val client: HttpClient) {

    suspend fun fetchConfiguration(): RevoltConfigurationApiModel = client.get(urlString = "https://api.revolt.chat/").body()
}