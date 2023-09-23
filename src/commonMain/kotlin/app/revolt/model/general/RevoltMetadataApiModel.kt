package app.revolt.model.general

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Polymorphic
sealed class RevoltMetadataApiModel {

    @Serializable
    @SerialName("File")
    data object File: RevoltMetadataApiModel()

    @Serializable
    @SerialName("Text")
    data object Text: RevoltMetadataApiModel()

    @Serializable
    @SerialName("Audio")
    data object Audio: RevoltMetadataApiModel()

    @Serializable
    @SerialName("Image")
    data class Image(val width: Int, val height: Int): RevoltMetadataApiModel()

    @Serializable
    @SerialName("Video")
    data class Video(val width: Int, val height: Int): RevoltMetadataApiModel()
}