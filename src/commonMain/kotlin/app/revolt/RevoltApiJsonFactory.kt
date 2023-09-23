package app.revolt

import app.revolt.model.auth.session.response.RevoltLoginApiResponse
import app.revolt.model.general.RevoltMetadataApiModel
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

internal object RevoltApiJsonFactory {

    fun create(): Json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        isLenient = true
        allowSpecialFloatingPointValues = true
        prettyPrint = false
        serializersModule = SerializersModule {
            polymorphic(
                RevoltLoginApiResponse::class,
                RevoltLoginApiResponse.Success::class,
                RevoltLoginApiResponse.Success.serializer()
            )
            polymorphic(
                RevoltLoginApiResponse::class,
                RevoltLoginApiResponse.MFA::class,
                RevoltLoginApiResponse.MFA.serializer()
            )
            polymorphic(
                RevoltLoginApiResponse::class,
                RevoltLoginApiResponse.Disabled::class,
                RevoltLoginApiResponse.Disabled.serializer()
            )
            polymorphic(
                RevoltMetadataApiModel::class,
                RevoltMetadataApiModel.File::class,
                RevoltMetadataApiModel.File.serializer()
            )
            polymorphic(
                RevoltMetadataApiModel::class,
                RevoltMetadataApiModel.Audio::class,
                RevoltMetadataApiModel.Audio.serializer()
            )
            polymorphic(
                RevoltMetadataApiModel::class,
                RevoltMetadataApiModel.Text::class,
                RevoltMetadataApiModel.Text.serializer()
            )
            polymorphic(
                RevoltMetadataApiModel::class,
                RevoltMetadataApiModel.Image::class,
                RevoltMetadataApiModel.Image.serializer()
            )
            polymorphic(
                RevoltMetadataApiModel::class,
                RevoltMetadataApiModel.Video::class,
                RevoltMetadataApiModel.Video.serializer()
            )
        }
    }
}