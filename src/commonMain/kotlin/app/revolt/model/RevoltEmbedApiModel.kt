package app.revolt.model

import app.revolt.model.general.RevoltFileApiModel
import app.revolt.utils.RevoltApiConstants
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Polymorphic
sealed class RevoltEmbedApiModel {

    @Serializable
    @SerialName("None")
    data object None : RevoltEmbedApiModel()

    @Serializable
    @SerialName("Website")
    data class Website(
        val url: String? = null,
        @SerialName("original_url")
        val originalUrl: String? = null,
        val special: Special? = null,
        val title: String? = null,
        val description: String? = null,
        val image: Image? = null,
        val video: Video? = null,
        @SerialName("site_name")
        val siteName: String? = null,
        @SerialName("icon_url")
        val iconUrl: String? = null,
        @SerialName(RevoltApiConstants.COLOUR)
        val color: String? = null,
    ) {

        @Serializable
        @Polymorphic
        sealed interface Special {

            @Serializable
            @SerialName("None")
            data object None : Special

            @Serializable
            @SerialName("GIF")
            data object GIF : Special

            @Serializable
            @SerialName("YouTube")
            data class Youtube(val id: String, val timestamp: String? = null) : Special

            @Serializable
            @SerialName("Lightspeed")
            data class Lightspeed(
                val id: String,
                @SerialName("content_type")
                val contentType: Type
            ) : Special {

                @Serializable
                enum class Type {
                    Channel
                }
            }

            @Serializable
            @SerialName("Twitch")
            data class Twitch(
                val id: String,
                @SerialName("content_type")
                val contentType: Type
            ) : Special {

                @Serializable
                enum class Type {
                    Channel,
                    Video,
                    Clip
                }
            }

            @Serializable
            @SerialName("Spotify")
            data class Spotify(
                val id: String,
                @SerialName("content_type")
                val contentType: String
            ) : Special

            @Serializable
            @SerialName("Soundcloud")
            data object Soundcloud : Special

            @Serializable
            @SerialName("Bandcamp")
            data class Bandcamp(
                val id: String,
                @SerialName("content_type")
                val contentType: Type
            ) : Special {

                @Serializable
                enum class Type {
                    Album,
                    Track
                }
            }

            @Serializable
            @SerialName("Streamable")
            data class Streamable(val id: String) : Special
        }
    }

    @Serializable
    @SerialName("Image")
    data class Image(val url: String, val width: Int, val height: Int, val size: Size) : RevoltEmbedApiModel() {

        @Serializable
        enum class Size {
            Large,
            Preview
        }
    }

    @Serializable
    @SerialName("Video")
    data class Video(val url: String, val width: Int, val height: Int) : RevoltEmbedApiModel()

    @Serializable
    @SerialName("Text")
    data class Text(
        val iconUrl: String? = null,
        val url: String? = null,
        val title: String? = null,
        val description: String? = null,
        val media: RevoltFileApiModel? = null,
        @SerialName(RevoltApiConstants.COLOUR)
        val color: String? = null
    )
}