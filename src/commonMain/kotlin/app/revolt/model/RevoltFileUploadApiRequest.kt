package app.revolt.model

data class RevoltFileUploadApiRequest(
    val fileName: String,
    val bytes: ByteArray,
    val contentType: ContentType
) {
    enum class ContentType {
        JPEG,
        JPG,
        PNG,
        GIF
    }

    internal fun getKtorContentType(): io.ktor.http.ContentType {
        return when (contentType) {
            ContentType.JPEG -> io.ktor.http.ContentType.Image.JPEG
            ContentType.JPG -> io.ktor.http.ContentType.Image.JPEG
            ContentType.PNG -> io.ktor.http.ContentType.Image.PNG
            ContentType.GIF -> io.ktor.http.ContentType.Image.GIF
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as RevoltFileUploadApiRequest

        if (fileName != other.fileName) return false
        if (!bytes.contentEquals(other.bytes)) return false
        if (contentType != other.contentType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fileName.hashCode()
        result = 31 * result + bytes.contentHashCode()
        result = 31 * result + contentType.hashCode()
        return result
    }

}