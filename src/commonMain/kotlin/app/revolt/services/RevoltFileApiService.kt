package app.revolt.services

import app.revolt.model.RevoltFileUploadApiRequest
import app.revolt.model.RevoltFileUploadApiResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.http.content.*

class RevoltFileApiService(private val client: HttpClient) {

    suspend fun uploadFile(url: String, file: RevoltFileUploadApiRequest): RevoltFileUploadApiResponse {
        val parts: List<PartData> = formData {
            // Regular form parameter
            append("file", file.bytes, Headers.build {
                append(HttpHeaders.ContentType, ContentType.Image.JPEG)
                append(HttpHeaders.ContentDisposition, "filename=1234123.jpeg")
            })
        }

        return client.submitFormWithBinaryData(formData = parts /* prepared parts */) {
            url(url)
            headers.append(HttpHeaders.ContentType, ContentType.MultiPart.FormData)
        }.body()
    }
}